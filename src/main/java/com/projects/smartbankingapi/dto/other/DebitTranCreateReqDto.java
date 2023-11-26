package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DebitTranCreateReqDto {
    @NotNull
    @Max(value = 1000000, message = "Amount must be less than 1000000")
    @Min(value = 0, message = "Amount must be greater than 0")
    private Float amount;
    @NotNull(message = "From account number is required")
    private String fromAccountNo; // from account
    @NotNull(message = "To account number is required")
    private String toAccountNo; // to account
}