package com.github.katemerek.bank_wallet.model;

import com.github.katemerek.bank_wallet.enumiration.OperationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "operation")
@Getter
@Setter
@ToString(exclude = "wallet")
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotBlank(message = "Please enter the type of operation: DEPOSIT or WITHDRAW")
    private OperationType operationType;

    @Column(name = "amount")
    @Min(value = 1, message = "Please enter the amount of money for the transaction greater than zero")
    private double amount;

    @Column(name = "balance")
    private double balance;
}
