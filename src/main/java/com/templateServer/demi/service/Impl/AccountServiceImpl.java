package com.templateServer.demi.service.Impl;

import com.templateServer.demi.constant.AccountsConstants;
import com.templateServer.demi.dto.request.AccountRequest;
import com.templateServer.demi.dto.request.CustomerRequest;
import com.templateServer.demi.entity.Account;
import com.templateServer.demi.entity.Customer;
import com.templateServer.demi.exception.CustomerAlreadyExistsException;
import com.templateServer.demi.exception.ResourceNotFoundException;
import com.templateServer.demi.repository.AccountRepository;
import com.templateServer.demi.repository.CustomerRepository;
import com.templateServer.demi.service.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerRequest createAccount(CustomerRequest customerRequest) {
        Optional<Customer> customerByPhoneNumber = customerRepository.findByMobileNumber(customerRequest.getMobileNumber());

        if (customerByPhoneNumber.isPresent()) {
            throw new CustomerAlreadyExistsException("This customer already exists " + customerRequest.getMobileNumber());
        }

        Customer customer = mapToCustomer(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        Account account = mapToAccount(savedCustomer);
        accountRepository.save(account);

        return modelMapper.map(customer, CustomerRequest.class);
    }

    @Override
    public CustomerRequest fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customer id", customer.getCustomerId().toString()));

        return mapToCustomerDto(customer, account);
    }

    @Override
    public boolean updateAccount(CustomerRequest request) {
        AccountRequest accountRequest = request.getAccountRequest();
        Account account = accountRepository.findById(accountRequest.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "account number", accountRequest.getAccountNumber().toString()));
        modelMapper.map(accountRequest, account);
        accountRepository.save(account);

        Customer customer = customerRepository.findById(account.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", account.getCustomerId().toString()));
        modelMapper.map(request, customer);
        customerRepository.save(customer);

        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        accountRepository.deleteAccountByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);

        return true;
    }

    private CustomerRequest mapToCustomerDto(Customer customer, Account account) {
        CustomerRequest customerRequest = modelMapper.map(customer, CustomerRequest.class);
        AccountRequest accountRequest = modelMapper.map(account, AccountRequest.class);
        customerRequest.setAccountRequest(accountRequest);

        return customerRequest;
    }

    private Customer mapToCustomer(CustomerRequest customerRequest) {

        return modelMapper.map(customerRequest, Customer.class);
    }

    private Account mapToAccount(Customer customer) {
        Account account = modelMapper.map(customer, Account.class);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);

        return account;
    }
}
