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
@Table(name = "BN_R_LOAN_PRODUCT", indexes = {
        @Index(name = "BN_R_LOAN_PRODUCT_LOAN_PRODUCT_ID_IDX", columnList = "LOAN_PRODUCT_ID"),
        @Index(name = "BN_R_LOAN_PRODUCT_LOAN_TYPE_ID_IDX", columnList = "LOAN_TYPE_ID"),
        @Index(name = "BN_R_LOAN_PRODUCT_INT_RATE_ID_IDX", columnList = "INT_RATE_ID"),
        @Index(name = "BN_R_LOAN_PRODUCT_LOAN_PERIOD_ID_IDX", columnList = "LOAN_PERIOD_ID")
})
public class BnRLoanProduct extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_LOAN_PRODUCT", sequenceName = "BN_R_LOAN_PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_LOAN_PRODUCT_SEQ")
    @Column(name = "LOAN_PRODUCT_ID", nullable = false)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_TYPE_ID", nullable = false, referencedColumnName = "LOAN_TYPE_ID")
    private BnRLoanType bnRLoanType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INT_RATE_ID", nullable = false, referencedColumnName = "INT_RATE_ID")
    private BnRIntRate bnRIntRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_PERIOD_ID", nullable = false, referencedColumnName = "LOAN_PERIOD_ID")
    private BnRLoanPeriod bnRLoanPeriod;

}