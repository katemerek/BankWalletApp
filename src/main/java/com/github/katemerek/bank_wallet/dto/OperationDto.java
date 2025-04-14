package com.github.katemerek.bank_wallet.dto;

import com.github.katemerek.bank_wallet.enumiration.OperationType;
import com.github.katemerek.bank_wallet.model.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {
    @NotBlank(message = "Please enter the UUID of wallet")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
    private UUID walletId;

    @NotBlank(message = "Please enter the type of operation: DEPOSIT or WITHDRAW")
    private OperationType operationType;

    @Column(name = "amount")
    @Min(value = 1, message = "Please enter the amount of money for the transaction greater than zero")
    private double amount;
}
