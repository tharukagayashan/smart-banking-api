package com.projects.smartbankingapi.model.reference;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_R_BRANCH", indexes = {
        @Index(name = "BN_R_BRANCH_BRANCH_ID_IDX", columnList = "BRANCH_ID"),
        @Index(name = "BN_R_BRANCH_BRANCH_CODE_IDX", columnList = "BRANCH_CODE", unique = true),
        @Index(name = "BN_R_BRANCH_BANK_ID_IDX", columnList = "BANK_ID")
})
public class BnRBranch extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_BRANCH")
    @SequenceGenerator(name = "BN_R_BRANCH", sequenceName = "BN_R_BRANCH_SEQ", allocationSize = 1)
    @Column(name = "BRANCH_ID")
    private Long branchId;

    @Column(name = "BRANCH_NAME", length = 50)
    private String name;

    @Column(name = "BRANCH_CODE", length = 5, unique = true)
    private String code;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID", referencedColumnName = "BANK_ID", nullable = false)
    private BnRBank bnRBank;

}