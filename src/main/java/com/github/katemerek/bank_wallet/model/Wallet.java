package com.github.katemerek.bank_wallet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @Column(name = "wallet_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID walletId;

    @Column(name = "balance")
    @PositiveOrZero(message = "Balance cannot be negative")
    private double balance;
}
