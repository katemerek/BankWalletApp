package com.github.katemerek.bank_wallet.service;

import com.github.katemerek.bank_wallet.model.Operation;
import com.github.katemerek.bank_wallet.repository.OperationRepository;
import com.github.katemerek.bank_wallet.util.InsufficientFundsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OperationService {
    private final OperationRepository operationRepository;

    @Transactional
    public Long add(Operation operation) {
        operationRepository.save(operation);
        return operation.getId();
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Transactional
    public Long save(Operation operation) {
        boolean checkBalance = operationRepository.saveWithUpdateBalance(operation);
        if (!checkBalance) {
            throw new InsufficientFundsException();
        }
        return operation.getId();
    }
}
