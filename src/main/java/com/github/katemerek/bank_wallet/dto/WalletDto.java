package com.github.katemerek.bank_wallet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto {
    @PositiveOrZero(message = "Balance cannot be negative")
    @NotNull(message = "Please enter the balance of wallet")
    private double balance;
}
