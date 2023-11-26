package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankDepositTranCreateReqDto {
    @Max(value = 1000000, message = "Amount must be less than 1000000")
    @Min(value = 0, message = "Amount must be greater than 0")
    private Float amount;
    @NotNull
    @NotEmpty
    @NotBlank
    private String toAccountNo; // to account
}