package com.github.katemerek.bank_wallet.controller;

import com.github.katemerek.bank_wallet.dto.OperationDto;
import com.github.katemerek.bank_wallet.mapper.OperationMapper;
import com.github.katemerek.bank_wallet.model.Operation;
import com.github.katemerek.bank_wallet.response.IdResponseOperation;
import com.github.katemerek.bank_wallet.service.OperationService;
import com.github.katemerek.bank_wallet.util.WalletNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OperationController {
    private final OperationService operationService;
    private final OperationMapper operationMapper;

    @GetMapping("/operations")
    public List<OperationDto> getAllOperations() {
        return operationService.getAllOperations()
                .stream()
                .map(operationMapper::toOperationDto)
                .toList();
    }

    @PostMapping("/wallet")
    public ResponseEntity<IdResponseOperation> addOperation(@RequestBody @Valid OperationDto operationDto) throws WalletNotFoundException {
        Operation operationToAdd = operationMapper.toOperation(operationDto);
        operationService.add(operationToAdd);
        IdResponseOperation response = new IdResponseOperation(
                operationToAdd.getId(),
                "New operation for wallet " + operationDto.getWalletId() + "created successfully"
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
