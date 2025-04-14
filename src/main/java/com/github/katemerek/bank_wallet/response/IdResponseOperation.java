package com.github.katemerek.bank_wallet.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class IdResponseOperation {

    private Long id;

    private String message;
}
