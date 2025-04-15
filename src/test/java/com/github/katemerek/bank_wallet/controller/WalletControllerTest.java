package com.github.katemerek.bank_wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.katemerek.bank_wallet.dto.WalletDto;
import com.github.katemerek.bank_wallet.mapper.WalletMapper;
import com.github.katemerek.bank_wallet.model.Wallet;
import com.github.katemerek.bank_wallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private WalletService walletService;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    private WalletMapper walletMapper;

    private List<Wallet> getWallets() {
        Wallet one = new Wallet();
        Wallet two = new Wallet();
        return List.of(one, two);
    }

    private WalletDto createWalletDto() {
        WalletDto walletDto = new WalletDto();
        walletDto.setBalance(100);
        return walletDto;
    }

    private Wallet createWallet() {
        Wallet wallet = new Wallet();
        wallet.setWalletId(UUID.fromString("98be9c00-454a-4167-9a1e-8e853a4cf1f5"));
        wallet.setBalance(100);
        return wallet;
    }

    private WalletDto createInvalidWalletDto() {
        WalletDto invalidDto = new WalletDto();
        invalidDto.setBalance(-200);
        return invalidDto;
    }

    @Test
    void getAllWallets_ShouldReturnListOfWalletDtos() throws Exception {
        when(walletService.getAllWallets()).thenReturn(getWallets());

        mvc.perform(get("/api/v1/wallets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void addWallet_ShouldReturnCreatedStatus() throws Exception {
        when(walletMapper.toWallet(any(WalletDto.class))).thenReturn(createWallet());
        when(walletService.add(any(Wallet.class))).thenReturn(createWallet().getWalletId());

        mvc.perform(post("/api/v1/add_wallets")
                        .content(mapper.writeValueAsString(createWalletDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void addWallet_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        mvc.perform(post("/api/v1/add_wallets")
                        .content(mapper.writeValueAsString(createInvalidWalletDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWalletById_ShouldReturnWallet() throws Exception {
        doReturn(Optional.of(createWallet())).when(walletService).getWalletById(any(UUID.class));
        when(walletMapper.toWalletDto(any(Wallet.class))).thenReturn(createWalletDto());

        mvc.perform(get("/api/v1/wallets/{WalletId}", createWallet().getWalletId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.balance").value(100));
    }
}
