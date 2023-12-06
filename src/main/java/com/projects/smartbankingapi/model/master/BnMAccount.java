package com.projects.smartbankingapi.model.master;

import com.projects.smartbankingapi.audit.AuditModel;
import com.projects.smartbankingapi.model.reference.BnRAccountType;
import com.projects.smartbankingapi.model.reference.BnRBranch;
import com.projects.smartbankingapi.model.reference.BnRCurrency;
import com.projects.smartbankingapi.model.reference.BnRStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_M_ACCOUNT", indexes = {
        @Index(name = "BN_M_ACCOUNT_ACCOUNT_ID_IDX", columnList = "ACCOUNT_ID"),
        @Index(name = "BN_M_ACCOUNT_ACCOUNT_NO_UNIQUE_IDX", columnList = "ACCOUNT_NO", unique = true),
        @Index(name = "BN_M_ACCOUNT_CUSTOMER_ID_IDX", columnList = "CUSTOMER_ID"),
        @Index(name = "BN_M_ACCOUNT_ACCOUNT_TYPE_ID_IDX", columnList = "ACCOUNT_TYPE_ID"),
        @Index(name = "BN_M_ACCOUNT_STATUS_ID_IDX", columnList = "STATUS_ID"),
        @Index(name = "BN_M_ACCOUNT_CURRENCY_ID_IDX", columnList = "CURRENCY_ID"),
        @Index(name = "BN_M_ACCOUNT_BRANCH_ID_IDX", columnList = "BRANCH_ID")
})
public class BnMAccount extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_M_ACCOUNT", sequenceName = "BN_M_ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_M_ACCOUNT")
    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long accountId;

    @Column(name = "ACCOUNT_NO", length = 25, unique = true)
    private String accountNo;

    @Column(name = "CURRENT_BAL", nullable = false)
    private Float currentBalance;

    @Column(name = "AVAILABLE_BAL", nullable = false)
    private Float availableBalance;

    @Column(name = "HOLD_BAL", nullable = false)
    private Float holdBalance;

    @Column(name = "OPEN_DATE", updatable = false)
    private LocalDate openedDate;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID", nullable = false)
    private BnMCustomer bnMCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID", referencedColumnName = "ACCOUNT_TYPE_ID", nullable = false)
    private BnRAccountType bnRAccountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID", nullable = false)
    private BnRStatus bnRStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID", referencedColumnName = "CURRENCY_ID", nullable = false)
    private BnRCurrency bnRCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "BRANCH_ID", nullable = false)
    private BnRBranch bnRBranch;

}