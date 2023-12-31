package com.projects.smartbankingapi.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.reference.BnRBranch}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnRBranchDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long branchId;
    private String name;
    private String code;
    private Boolean isActive;

    private Long bankId;

    private BnRBankDto bnRBank;
}