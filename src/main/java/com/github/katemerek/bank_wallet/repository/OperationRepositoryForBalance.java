package com.github.katemerek.bank_wallet.repository;

import com.github.katemerek.bank_wallet.model.Operation;

public interface OperationRepositoryForBalance {
    boolean saveWithUpdateBalance(Operation operation);
}
