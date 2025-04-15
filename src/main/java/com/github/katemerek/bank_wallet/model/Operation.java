package com.github.katemerek.bank_wallet.model;

import com.github.katemerek.bank_wallet.enumiration.TypeOfOperation;
import com.github.katemerek.bank_wallet.util.TypeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @SequenceGenerator(name = "operation_seq", sequenceName = "operation_sequence", initialValue = 25, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_seq") //в миграции БД для облегчения проверки добавлено 24 строки тестовых данных, поэтому вставка начинается с 25
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Convert(converter = TypeConverter.class)
    @Column(columnDefinition = "type_of_operation")
    private TypeOfOperation typeOfOperation;

    @Column(name = "amount")
    @Min(value = 1, message = "Please enter the amount of money for the transaction greater than zero")
    private double amount;
}
