package com.projects.smartbankingapi.model.transaction;

import com.projects.smartbankingapi.audit.AuditModel;
import com.projects.smartbankingapi.model.master.BnMAccount;
import com.projects.smartbankingapi.model.reference.BnRTranType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_T_TRAN", indexes = {
        @Index(name = "BN_T_TRAN_TRAN_ID_IDX", columnList = "TRAN_ID"),
        @Index(name = "BN_T_TRAN_TRAN_TYPE_ID_IDX", columnList = "TRAN_TYPE_ID"),
        @Index(name = "BN_T_TRAN_ACCOUNT_ID_IDX", columnList = "ACCOUNT_ID")
})
public class BnTTran extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_T_TRAN", sequenceName = "BN_T_TRAN_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_T_TRAN_SEQ")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false, referencedColumnName = "ACCOUNT_ID")
    private BnMAccount bnMAccount;

}
