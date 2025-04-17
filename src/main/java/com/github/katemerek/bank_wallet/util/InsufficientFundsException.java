package com.github.katemerek.bank_wallet.util;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Insufficient funds to carry out the operation");
    }
}
