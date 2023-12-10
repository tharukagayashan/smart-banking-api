package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculatorReqDto {
    @NotNull
    private Integer months;
    @NotNull
    @Min(0)
    private Float interestRate;
    @NotNull
    @Min(0)
    private Float loanAmount;
    @NotNull
    private Integer loanTypeId;
}