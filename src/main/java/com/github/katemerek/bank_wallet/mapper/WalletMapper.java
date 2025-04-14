package com.github.katemerek.bank_wallet.mapper;

import com.github.katemerek.bank_wallet.dto.WalletDto;
import com.github.katemerek.bank_wallet.model.Operation;
import com.github.katemerek.bank_wallet.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WalletMapper {
    Wallet toWallet(WalletDto walletDto);
    WalletDto toWalletDto(Wallet wallet);
}
