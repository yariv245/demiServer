package com.templateServer.demi.service;


import com.templateServer.demi.dto.CustomerDto;

public interface AccountService {

    /**
     *  method for creating new account
     * @param customerDto
     * @return saved customer details
     */
    CustomerDto createAccount(CustomerDto customerDto);

    /**
     *  method for creating new account
     * @param mobileNumber
     * @return customer details
     */
    CustomerDto fetchCustomerDetails(String mobileNumber);
}
