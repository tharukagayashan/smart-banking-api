package com.projects.smartbankingapi.model.transaction;

import com.projects.smartbankingapi.audit.AuditModel;
import com.projects.smartbankingapi.model.master.BnMLoan;
import com.projects.smartbankingapi.model.reference.BnRLoanPayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BN_T_LOAN_TRAN")
public class BnTLoanTran extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_T_LOAN_TRAN", sequenceName = "BN_T_LOAN_TRAN_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_T_LOAN_TRAN")
    @Column(name = "LOAN_TRAN_ID", nullable = false)
    private Long loanTranId;

    @Column(name = "AMOUNT", nullable = false)
    private Float amount;

    @Column(name = "DESCRIPTION", nullable = false, length = 200)
    private String description;

    @Column(name = "TRAN_REFERENCE", length = 200)
    private String tranReference;

    @Column(name = "TRAN_DATE", nullable = false)
    private LocalDate tranDate;

    @Column(name = "TRAN_TIME", nullable = false)
    private LocalTime tranTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_ID", nullable = false, referencedColumnName = "LOAN_ID")
    private BnMLoan bnMLoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_PAY_TYPE_ID", nullable = false, referencedColumnName = "LOAN_PAY_TYPE_ID")
    private BnRLoanPayType bnRLoanPayType;

}
