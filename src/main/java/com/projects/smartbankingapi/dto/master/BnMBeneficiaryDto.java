package com.projects.smartbankingapi.dto.master;

import com.projects.smartbankingapi.dto.reference.BnRBranchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.master.BnMBeneficiary}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnMBeneficiaryDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long beneficiaryId;
    private String name;
    private String accountNo;
    private String nic;
    private String mobileNo;
    private String email;
    private BnMAccountDto bnMAccount;
    private BnRBranchDto bnRBranch;
}