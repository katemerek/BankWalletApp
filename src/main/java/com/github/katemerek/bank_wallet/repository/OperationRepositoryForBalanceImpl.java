package com.github.katemerek.bank_wallet.repository;

import com.github.katemerek.bank_wallet.model.Operation;
import com.github.katemerek.bank_wallet.model.Wallet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import static com.github.katemerek.bank_wallet.enumiration.TypeOfOperation.DEPOSIT;
import static com.github.katemerek.bank_wallet.enumiration.TypeOfOperation.WITHDRAW;

public class OperationRepositoryForBalanceImpl implements OperationRepositoryForBalance{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveWithUpdateBalance(Operation operation) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        // 1. Проверка баланса для WITHDRAW
        if (operation.getTypeOfOperation() == WITHDRAW) {
            CriteriaQuery<Double> balanceQuery = cb.createQuery(Double.class);
            Root<Wallet> walletRoot = balanceQuery.from(Wallet.class);
            balanceQuery.select(walletRoot.get("balance"))
                    .where(cb.equal(walletRoot.get("walletId"),
                            operation.getWallet().getWalletId()));

            Double currentBalance = entityManager.createQuery(balanceQuery)
                    .getSingleResult();

            if (currentBalance.compareTo(operation.getAmount()) < 0) {
                return false; // Недостаточно средств
            }
        }
        // 2. Обновление баланса
        CriteriaUpdate<Wallet> update = cb.createCriteriaUpdate(Wallet.class);
        Root<Wallet> walletRoot = update.from(Wallet.class);

        update.set("balance",
                        cb.sum(walletRoot.get("balance"),
                                operation.getTypeOfOperation() == DEPOSIT ?
                                        operation.getAmount() :
                                        -(operation.getAmount())))
                .where(cb.equal(walletRoot.get("walletId"),
                        operation.getWallet().getWalletId()));

        entityManager.createQuery(update).executeUpdate();

        return true;
    }
}
