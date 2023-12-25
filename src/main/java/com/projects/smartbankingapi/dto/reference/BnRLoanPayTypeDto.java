package com.projects.smartbankingapi.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnRLoanPayTypeDto {
    private Long loanPayTypeId;
    private String payType;
    private String payTypeCode;
}