package com.templateServer.demi.controller;

import com.templateServer.demi.constant.AccountsConstants;
import com.templateServer.demi.dto.CustomerDto;
import com.templateServer.demi.dto.ResponseDto;
import com.templateServer.demi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto request) {
        CustomerDto customer = accountService.createAccount(request);

        return ResponseEntity
                .created(URI.create(customer.getName()))
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}
