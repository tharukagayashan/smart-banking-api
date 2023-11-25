package com.projects.smartbankingapi.model.master;

import com.projects.smartbankingapi.audit.AuditModel;
import com.projects.smartbankingapi.model.reference.BnRBranch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_M_BENEFICIARY", indexes = {
        @Index(name = "BN_R_BENEFICIARY_BENEFICIARY_ID_IDX", columnList = "BENEFICIARY_ID"),
        @Index(name = "BN_R_BENEFICIARY_BRANCH_ID_IDX", columnList = "BRANCH_ID"),
        @Index(name = "BN_R_BENEFICIARY_ACCOUNT_ID_IDX", columnList = "ACCOUNT_ID")
})
public class BnMBeneficiary extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_M_BENEFICIARY", sequenceName = "BN_M_BENEFICIARY_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "BN_M_BENEFICIARY", strategy = GenerationType.SEQUENCE)
    @Column(name = "BENEFICIARY_ID", nullable = false)
    private Long beneficiaryId;

    @Column(name = "FULL_NAME", length = 100)
    private String name;

    @Column(name = "ACCOUNT_NO", length = 25)
    private String accountNo;

    @Column(name = "NIC", length = 12)
    private String nic;

    @Column(name = "MOBILE_NO", length = 10)
    private String mobileNo;

    @Column(name = "EMAIL", length = 60)
    private String email;

    //Associate account number
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID", nullable = false)
    private BnMAccount bnMAccount;

    //Beneficiary branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "BRANCH_ID", nullable = false)
    private BnRBranch bnRBranch;

}