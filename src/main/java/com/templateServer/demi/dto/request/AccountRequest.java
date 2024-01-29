package com.templateServer.demi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountRequest {
    @Min(value = 1)
    private Long accountNumber;
    @NotBlank(message = "AccountType can not be a null or blank")
    private String accountType;
    @NotBlank(message = "BranchAddress can not be a null or blank")
    private String branchAddress;
}
