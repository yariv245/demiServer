package com.templateServer.demi.service;


import com.templateServer.demi.dto.request.CustomerRequest;

public interface AccountService {

    /**
     *  method for creating new account
     * @param customerRequest  customer details
     * @return saved customer details
     */
    CustomerRequest createAccount(CustomerRequest customerRequest);

    /**
     *  method for fetch account details
     * @param mobileNumber customer mobile number
     * @return customer details
     */
    CustomerRequest fetchCustomerDetails(String mobileNumber);

    /**
     *  method for updating account
     * @param customerRequest customer details
     * @return boolean succeed
     */
    boolean updateAccount(CustomerRequest customerRequest);

    /**
     *  deleting account
     * @param mobileNumber customer mobile number
     * @return boolean deleted
     */
    boolean deleteAccount(String mobileNumber);
}
