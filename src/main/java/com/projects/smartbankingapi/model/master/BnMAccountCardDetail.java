package com.projects.smartbankingapi.model.master;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_M_ACC_CARD_DETAILS",indexes = {
        @Index(name = "BN_M_ACC_CARD_DETAILS_ACC_CARD_ID",columnList = "ACC_CARD_ID"),
        @Index(name = "BN_M_ACC_CARD_DETAILS_ACCOUNT_ID",columnList = "ACCOUNT_ID"),
        @Index(name = "BN_M_ACC_CARD_DETAILS_CARD_ID",columnList = "CARD_ID")
})
public class BnMAccountCardDetail extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_M_ACC_CARD_DETAIL", sequenceName = "BN_M_ACC_CARD_DETAIL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_M_ACC_CARD_DETAIL_SEQ")
    @Column(name = "ACC_CARD_ID", nullable = false)
    private Long accCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID", nullable = false)
    private BnMAccount bnMAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID", nullable = false)
    private BnMCardDetail bnMCardDetail;

}
