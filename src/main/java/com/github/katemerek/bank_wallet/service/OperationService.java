package com.github.katemerek.bank_wallet.service;

import com.github.katemerek.bank_wallet.enumiration.TypeOfOperation;
import com.github.katemerek.bank_wallet.model.Operation;
import com.github.katemerek.bank_wallet.repository.OperationRepository;
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

    public List<Operation> getAllOperations() {return operationRepository.findAll();}

}
