package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanDisburseReqDto {
    @NotNull
    private Long loanId;
    @NotNull
    @Min(0)
    private Float disburseAmt;
}