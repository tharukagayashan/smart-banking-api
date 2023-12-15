package com.projects.smartbankingapi.model.reference;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BN_R_CURRENCY_RATE", indexes = {
        @Index(name = "BN_R_CURRENCY_RATE_CURRENCY_ID_IDX", columnList = "CURRENCY_ID")
})
public class BnRCurrencyRate extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_CURRENCY_RATE", sequenceName = "BN_R_CURRENCY_RATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_CURRENCY_RATE")
    @Column(name = "CURRENCY_RATE_ID", nullable = false)
    private Long currencyRateId;

    @Column(name = "SELLING_RATE")
    private Float sellingRate;

    @Column(name = "BUYING_RATE")
    private Float buyingRate;

    @Column(name = "MIDDLE_RATE")
    private Float middleRate;

    @Column(name = "PUBLICATION_DATE")
    private LocalDate publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID", referencedColumnName = "CURRENCY_ID", nullable = false)
    private BnRCurrency bnRCurrency;

}
