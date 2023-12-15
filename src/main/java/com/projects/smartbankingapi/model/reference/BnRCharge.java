package com.projects.smartbankingapi.model.reference;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BN_R_CHARGE", indexes = {
        @Index(name = "BN_R_CHARGE_CHARGE_ID_IDX", columnList = "CHARGE_ID"),
        @Index(name = "BN_R_CHARGE_FEE_TYPE_ID_IDX", columnList = "FEE_TYPE_ID"),
        @Index(name = "BN_R_CHARGE_CURRENCY_ID_IDX", columnList = "CURRENCY_ID")
})
public class BnRCharge extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_CHARGE", sequenceName = "BN_R_CHARGE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_CHARGE")
    @Column(name = "CHARGE_ID", nullable = false)
    private Long chargeId;

    @Column(name = "CHARGE_DESC", length = 100)
    private String description;

    @Column(name = "AMOUNT", nullable = false)
    private Float amount;

    @Column(name = "EFFECTIVE_DATE")
    private LocalDate effectiveDate;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEE_TYPE_ID", nullable = false, referencedColumnName = "FEE_TYPE_ID")
    private BnRFeeType bnRFeeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID", nullable = false, referencedColumnName = "CURRENCY_ID")
    private BnRCurrency bnRCurrency;

}