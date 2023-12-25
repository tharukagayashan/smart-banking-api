package com.projects.smartbankingapi.dto.master;

import com.projects.smartbankingapi.dto.reference.BnRBranchDto;
import com.projects.smartbankingapi.dto.reference.BnRRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnMStaffDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long staffId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String mobileNo;
    private String address;
    private Boolean isActive;

    @NotNull
    private Long roleId;
    @NotNull
    private Long branchId;

    private BnRRoleDto bnRRole;
    private BnRBranchDto bnRBranch;
}