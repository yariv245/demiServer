package com.templateServer.demi.service;


import com.templateServer.demi.dto.CustomerDto;

public interface AccountService {

    /**
     *  method for creating new account
     * @param customerDto customer
     * @return account details
     */
    CustomerDto createAccount(CustomerDto customerDto);
}
