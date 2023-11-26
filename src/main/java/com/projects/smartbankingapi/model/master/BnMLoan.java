package com.projects.smartbankingapi.model.master;

import com.projects.smartbankingapi.audit.AuditModel;
import com.projects.smartbankingapi.model.reference.BnRLoanProduct;
import com.projects.smartbankingapi.model.reference.BnRStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_M_LOAN", indexes = {
        @Index(name = "BN_M_LOAN_LOAN_ID_IDX", columnList = "LOAN_ID"),
        @Index(name = "BN_M_LOAN_STATUS_ID_IDX", columnList = "STATUS_ID"),
        @Index(name = "BN_M_LOAN_ACCOUNT_ID_IDX", columnList = "ACCOUNT_ID"),
        @Index(name = "BN_M_LOAN_LOAN_PRODUCT_ID_IDX", columnList = "LOAN_PRODUCT_ID")
})
public class BnMLoan extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_M_LOAN", sequenceName = "BN_M_LOAN_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "BN_M_LOAN", strategy = GenerationType.SEQUENCE)
    @Column(name = "LOAN_ID", nullable = false)
    private Long loanId;

    @Column(name = "AMOUNT", nullable = false)
    private Float amount;

    @Column(name = "INTEREST", nullable = false)
    private Float interest;

    @Column(name = "TOT_INSTALLMENTS")
    private Integer totInstallments;

    @Column(name = "NEXT_INSTALLMENT_DATE")
    private LocalDate nextInstallmentDate;

    @Column(name = "TOT_ARREARS_AMT", nullable = false)
    private Float totArrearsAmt;

    @Column(name = "REM_INSTALLMENTS")
    private Integer remInstallments;

    @Column(name = "NEXT_INSTALLMENT_AMT", nullable = false)
    private Float nextInstallmentAmt;

    @Column(name = "DISTRIBUTED_AMT", nullable = false)
    private Float distributedAmt;

    @Column(name = "TOT_SETTLED_AMT", nullable = false)
    private Float totSettledAmt;

    @Column(name = "TOT_INTEREST_PAID", nullable = false)
    private Float totInterestPaid;

    @Column(name = "TOT_PAID", nullable = false)
    private Float totPaid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID", nullable = false)
    private BnRStatus bnRStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID", nullable = false)
    private BnMAccount bnMAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_PRODUCT_ID", referencedColumnName = "LOAN_PRODUCT_ID", nullable = false)
    private BnRLoanProduct bnRLoanProduct;

}