package com.github.katemerek.bank_wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.katemerek.bank_wallet.dto.OperationDto;
import com.github.katemerek.bank_wallet.dto.WalletDto;
import com.github.katemerek.bank_wallet.mapper.OperationMapper;
import com.github.katemerek.bank_wallet.model.Operation;
import com.github.katemerek.bank_wallet.model.Wallet;
import com.github.katemerek.bank_wallet.service.OperationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static com.github.katemerek.bank_wallet.enumiration.TypeOfOperation.DEPOSIT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OperationController.class)
public class OperationControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private OperationService operationService;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    private OperationMapper operationMapper;

    private List<Operation> getOperations() {
        Operation one = new Operation();
        Operation two = new Operation();
        return List.of(one, two);
    }

    private OperationDto createOperationDto() {
        OperationDto operationDto = new OperationDto();
        operationDto.setType("DEPOSIT");
        operationDto.setWalletId(UUID.fromString("98be9c00-454a-4167-9a1e-8e853a4cf1f5"));
        operationDto.setAmount(500.0);
        return operationDto;
    }

    private Operation createOperation() {
        Operation operation = new Operation();
        operation.setId(11L);
        operation.setTypeOfOperation(DEPOSIT);
        operation.setWallet(new Wallet());
        operation.setAmount(500.0);
        return operation;
    }

    private OperationDto createInvalidOperationDto() {
        OperationDto invalidOperationDto = createOperationDto();
        invalidOperationDto.setAmount(-200.0);
        return invalidOperationDto;
    }

    @Test
    void getAllOperations_ShouldReturnListOfOperationDtos() throws Exception {
        when(operationService.getAllOperations()).thenReturn(getOperations());

        mvc.perform(get("/api/v1/operations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void addOperation_ShouldReturnCreatedStatus() throws Exception {
        when(operationMapper.toOperation(any(OperationDto.class))).thenReturn(createOperation());
        when(operationService.add(any(Operation.class))).thenReturn(createOperation().getId());

        mvc.perform(post("/api/v1/wallet")
                        .content(mapper.writeValueAsString(createOperationDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(11L));
    }

    @Test
    void addOperation_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        mvc.perform(post("/api/v1/wallet")
                        .content(mapper.writeValueAsString(createInvalidOperationDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}
