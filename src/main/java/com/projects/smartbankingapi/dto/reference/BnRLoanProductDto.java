package com.projects.smartbankingapi.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.reference.BnRLoanProduct}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnRLoanProductDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long productId;

    private Long loanTypeId;
    private Long intRateId;
    private Long periodId;

    private BnRLoanTypeDto bnRLoanType;
    private BnRIntRateDto bnRIntRate;
    private BnRLoanPeriodDto bnRLoanPeriod;
}