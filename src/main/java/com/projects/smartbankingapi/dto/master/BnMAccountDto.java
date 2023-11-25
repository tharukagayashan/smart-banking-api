package com.projects.smartbankingapi.dto.master;

import com.projects.smartbankingapi.dto.reference.BnRAccountTypeDto;
import com.projects.smartbankingapi.dto.reference.BnRBranchDto;
import com.projects.smartbankingapi.dto.reference.BnRCurrencyDto;
import com.projects.smartbankingapi.dto.reference.BnRStatusDto;
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
    private LocalDate openedDate;
    private BnMCustomerDto bnMCustomer;
    private BnRAccountTypeDto bnRAccountType;
    private BnRStatusDto bnRStatus;
    private BnRCurrencyDto bnRCurrency;
    private BnRBranchDto bnRBranch;
}