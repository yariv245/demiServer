package com.templateServer.demi.controller;

import com.templateServer.demi.constant.AccountsConstants;
import com.templateServer.demi.dto.request.CustomerRequest;
import com.templateServer.demi.dto.response.ResponseDto;
import com.templateServer.demi.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Validated
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerRequest request) {
        CustomerRequest customer = accountService.createAccount(request);

        return ResponseEntity
                .created(URI.create(customer.getName()))
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerRequest> fetchCustomerDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                                        String mobileNumber) {
        CustomerRequest response = accountService.fetchCustomerDetails(mobileNumber);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateAccount(@Valid @RequestBody CustomerRequest request) {
        boolean response = accountService.updateAccount(request);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount(@RequestParam
                                                 @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean response = accountService.deleteAccount(mobileNumber);

        return ResponseEntity
                .ok()
                .body(response);
    }
}
