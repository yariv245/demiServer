package com.templateServer.demi.service;


import com.templateServer.demi.dto.CustomerDto;

public interface AccountService {

    /**
     *  method for creating new account
     * @param customerDto  customer details
     * @return saved customer details
     */
    CustomerDto createAccount(CustomerDto customerDto);

    /**
     *  method for fetch account details
     * @param mobileNumber customer mobile number
     * @return customer details
     */
    CustomerDto fetchCustomerDetails(String mobileNumber);

    /**
     *  method for updating account
     * @param customerDto customer details
     * @return boolean succeed
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *  deleting account
     * @param mobileNumber customer mobile number
     * @return boolean deleted
     */
    boolean deleteAccount(String mobileNumber);
}
