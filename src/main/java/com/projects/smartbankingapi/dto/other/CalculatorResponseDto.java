package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculatorResponseDto {
    private Integer months;
    private Float totalInterest;
    private Float loanAmount;
    private Float totalPayable;
    private Float intRate;
}