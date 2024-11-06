package com.irsyad.demo_api.response;

import com.irsyad.demo_api.dto.AccountDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponse {
    private AccountDTO account;
}
