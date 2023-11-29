package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanProductCreateReqDto {
    @NotNull
    private Long loanTypeId;
    @NotNull
    private Long intRateId;
    @NotNull
    private Long periodId;
}
