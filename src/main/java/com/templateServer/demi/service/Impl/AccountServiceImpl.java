package com.templateServer.demi.service.Impl;

import com.templateServer.demi.dto.CustomerDto;
import com.templateServer.demi.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Override
    public CustomerDto createAccount(CustomerDto customerDto) {
        return null;
    }
}
