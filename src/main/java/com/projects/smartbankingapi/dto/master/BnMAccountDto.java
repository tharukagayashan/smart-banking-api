package com.projects.smartbankingapi.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.master.BnMAccount}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnMAccountDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long accountId;
    private String accountNo;
    private Float currentBalance;
    private Float availableBalance;
    private Float holdBalance;
    private LocalDate openedDate;
    private Boolean isActive;

    private Long customerId;
    private Long accountTypeId;
    private Long statusId;
    private Long currencyId;
    private Long branchId;

//    private BnMCustomerDto bnMCustomer;
//    private BnRAccountTypeDto bnRAccountType;
//    private BnRStatusDto bnRStatus;
//    private BnRCurrencyDto bnRCurrency;
//    private BnRBranchDto bnRBranch;
}