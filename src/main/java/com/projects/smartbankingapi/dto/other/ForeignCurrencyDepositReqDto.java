package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForeignCurrencyDepositReqDto {
    @Min(0)
    @NotNull
    private Float amount;
    @NotNull
    @NotEmpty
    @NotBlank
    private String toAccountNo;
    @NotNull
    private Long currencyId;
}