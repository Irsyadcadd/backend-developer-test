package com.irsyad.demo_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irsyad.demo_api.dto.AccountDTO;
import com.irsyad.demo_api.response.AccountResponse;
import com.irsyad.demo_api.response.ApiResponse;
import com.irsyad.demo_api.services.AccountService;


@RestController
@RequestMapping("/account")
public class AccountController {
   
    @Autowired
    private AccountService accountService;

    @GetMapping("/getaccount/{number}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccountByNumber(@PathVariable String number) {
        AccountDTO account = accountService.findAccountByNumber(number);
        AccountResponse accountResponse = new AccountResponse(account);
        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(accountResponse);
        apiResponse.setSuccess(true);
        return ResponseEntity.ok(apiResponse);
    }
    
}
