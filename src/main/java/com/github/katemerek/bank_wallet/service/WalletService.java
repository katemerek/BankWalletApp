package com.github.katemerek.bank_wallet.service;

import com.github.katemerek.bank_wallet.model.Wallet;
import com.github.katemerek.bank_wallet.repository.WalletRepository;
import com.github.katemerek.bank_wallet.util.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public List<Wallet> getAllWallets() {return walletRepository.findAll();}

    @Transactional
    public UUID add(Wallet wallet) {
        walletRepository.save(wallet);
        return wallet.getWalletId();
    }

    public Optional<Wallet> getWalletById(UUID id) {
        if (!walletRepository.existsById(id)) {
            throw new WalletNotFoundException(id);
        }
        return walletRepository.findById(id);
    }
}
