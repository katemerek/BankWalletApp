package com.github.katemerek.bank_wallet.util;

import java.util.UUID;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(UUID walletId) {
        super("Wallet not found with UUID: " + walletId);
    }
}
