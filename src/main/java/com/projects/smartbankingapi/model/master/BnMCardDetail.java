package com.projects.smartbankingapi.model.master;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BN_M_CARD_DETAIL", indexes = {
        @Index(name = "BN_M_CARD_DETAIL_CARD_ID_IDX", columnList = "CARD_ID"),
        @Index(name = "BN_M_CARD_DETAIL_CARD_NO_UNIQUE_IDX", columnList = "CARD_NO", unique = true)
})
public class BnMCardDetail extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_M_CARD_DETAIL", sequenceName = "BN_M_CARD_DETAIL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_M_CARD_DETAIL")
    @Column(name = "CARD_ID", nullable = false)
    private Long cardId;

    @Column(name = "CARD_TYPE", length = 20)
    private String cardType;

    @Column(name = "CARD_NO", length = 16, unique = true)
    private String cardNo;

    @Column(name = "EXPIRY_DATE", length = 10)
    private String expiryDate;

    @Column(name = "CVV", length = 3)
    private String cvv;

    @Column(name = "PIN", length = 4)
    private String pin;

}