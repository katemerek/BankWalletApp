package com.github.katemerek.bank_wallet.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {
    @NotNull(message = "Please enter the UUID of wallet")
//    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
    private UUID walletId;

    @NotBlank(message = "Please enter the type of transaction: DEPOSIT or WITHDRAW")
    private String type;

    @Column(name = "amount")
    @Min(value = 1, message = "Please enter the amount of money for the transaction greater than zero")
    private double amount;
}
