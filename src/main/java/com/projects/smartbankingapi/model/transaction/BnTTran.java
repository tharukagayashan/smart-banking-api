package com.projects.smartbankingapi.model.transaction;

import com.projects.smartbankingapi.audit.AuditModel;
import com.projects.smartbankingapi.model.reference.BnRBranch;
import com.projects.smartbankingapi.model.reference.BnRStatus;
import com.projects.smartbankingapi.model.reference.BnRTranType;
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
@Table(name = "BN_T_TRAN", indexes = {
        @Index(name = "BN_T_TRAN_TRAN_ID_IDX", columnList = "TRAN_ID"),
        @Index(name = "BN_T_TRAN_TRAN_TYPE_ID_IDX", columnList = "TRAN_TYPE_ID")
})
public class BnTTran extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_T_TRAN", sequenceName = "BN_T_TRAN_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_T_TRAN")
    @Column(name = "TRAN_ID", nullable = false)
    private Long tranId;

    @Column(name = "AMOUNT", nullable = false)
    private Float amount;

    @Column(name = "TRAN_DATE", nullable = false)
    private LocalDate tranDate;

    @Column(name = "TRAN_TIME", nullable = false)
    private LocalTime tranTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAN_TYPE_ID", nullable = false, referencedColumnName = "TRAN_TYPE_ID")
    private BnRTranType bnRTranType;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "FROM_ACCOUNT_NO", nullable = false)
    private String fromAccountNo;

    @Column(name = "TO_ACCOUNT_NO", nullable = false)
    private String toAccountNo;

    @Column(name = "TRAN_REFERENCE", length = 200)
    private String tranReference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID", nullable = false, referencedColumnName = "STATUS_ID")
    private BnRStatus bnRStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", nullable = false, referencedColumnName = "BRANCH_ID")
    private BnRBranch bnRBranch;

}
