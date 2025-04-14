package com.github.katemerek.bank_wallet.repository;

import com.github.katemerek.bank_wallet.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
