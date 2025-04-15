package com.github.katemerek.bank_wallet.mapper;

import com.github.katemerek.bank_wallet.dto.OperationDto;
import com.github.katemerek.bank_wallet.enumiration.TypeOfOperation;
import com.github.katemerek.bank_wallet.model.Operation;
import com.github.katemerek.bank_wallet.model.Wallet;
import com.github.katemerek.bank_wallet.repository.WalletRepository;
import com.github.katemerek.bank_wallet.util.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationMapper {
    private final WalletRepository walletRepository;

    public Operation toOperation(OperationDto operationDto) {
        Operation operation = new Operation();
        operation.setTypeOfOperation(TypeOfOperation.valueOf(operationDto.getType()));
        operation.setAmount(operationDto.getAmount());
        Wallet wallet = walletRepository.findById(operationDto.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException(operationDto.getWalletId()));
        operation.setWallet(wallet);

        return operation;
    }

    public OperationDto toOperationDto(Operation operation) {
        OperationDto operationDto = new OperationDto();
        operationDto.setType(String.valueOf(operation.getTypeOfOperation()));
        operationDto.setAmount(operation.getAmount());
        operationDto.setWalletId(operation.getWallet().getWalletId());

        return operationDto;
    }
}
