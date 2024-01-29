package com.templateServer.demi.service.Impl;

import com.templateServer.demi.constant.AccountsConstants;
import com.templateServer.demi.dto.AccountDto;
import com.templateServer.demi.dto.CustomerDto;
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
    public CustomerDto createAccount(CustomerDto customerDto) {
        Optional<Customer> customerByPhoneNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (customerByPhoneNumber.isPresent()) {
            throw new CustomerAlreadyExistsException("This customer already exists " + customerDto.getMobileNumber());
        }

        Customer customer = mapToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        Account account = mapToAccount(savedCustomer);
        accountRepository.save(account);

        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customer id", customer.getCustomerId().toString()));

        return mapToCustomerDto(customer, account);
    }

    @Override
    public boolean updateAccount(CustomerDto request) {
        AccountDto accountDto = request.getAccountDto();
        Account account = accountRepository.findById(accountDto.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "account number", accountDto.getAccountNumber().toString()));
        modelMapper.map(accountDto, account);
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

    private CustomerDto mapToCustomerDto(Customer customer, Account account) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        customerDto.setAccountDto(accountDto);

        return customerDto;
    }

    private Customer mapToCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Spring");

        return customer;
    }

    private Account mapToAccount(Customer customer) {
        Account account = modelMapper.map(customer, Account.class);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Spring");

        return account;
    }
}
