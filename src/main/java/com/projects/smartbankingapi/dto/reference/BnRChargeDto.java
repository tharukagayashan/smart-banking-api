package com.projects.smartbankingapi.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.reference.BnRCharge}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnRChargeDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long chargeId;
    private String description;
    private Float amount;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;

    private Long feeTypeId;
    private Long currencyId;

    private BnRFeeTypeDto bnRFeeType;
    private BnRCurrencyDto bnRCurrency;
}