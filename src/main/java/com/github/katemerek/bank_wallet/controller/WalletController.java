package com.github.katemerek.bank_wallet.controller;

import com.github.katemerek.bank_wallet.dto.WalletDto;
import com.github.katemerek.bank_wallet.mapper.WalletMapper;
import com.github.katemerek.bank_wallet.model.Wallet;
import com.github.katemerek.bank_wallet.response.IdResponse;
import com.github.katemerek.bank_wallet.service.WalletService;
import com.github.katemerek.bank_wallet.util.WalletNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WalletController {
    private final WalletService walletService;
    private final WalletMapper walletMapper;

    @GetMapping("/wallets")
    public List<WalletDto> getAllWallets() {
        return walletService.getAllWallets()
                .stream()
                .map(walletMapper::toWalletDto)
                .toList();
    }

    @GetMapping("/wallets/{walletId}")
    public Optional<WalletDto> getWallet(@PathVariable("walletId") UUID walletId) {
        return walletService.getWalletById(walletId).map(walletMapper::toWalletDto);
    }

    @PostMapping("/add_wallets")
    public ResponseEntity<IdResponse> createWallet (@RequestBody @Valid WalletDto walletDto) {
        Wallet walletToAdd = walletMapper.toWallet(walletDto);
        walletService.add(walletToAdd);
        IdResponse response = new IdResponse(
                walletToAdd.getWalletId(),
                "New wallet created successfully"
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
