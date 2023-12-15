package com.projects.smartbankingapi.model.reference;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BN_R_CURRENCY", indexes = {
        @Index(name = "BN_R_CURRENCY_CURRENCY_ID_IDX", columnList = "CURRENCY_ID"),
        @Index(name = "BN_R_CURRENCY_CURRENCY_CODE_IDX", columnList = "CURRENCY_CODE", unique = true)
})
public class BnRCurrency extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_CURRENCY", sequenceName = "BN_R_CURRENCY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_CURRENCY")
    @Column(name = "CURRENCY_ID", nullable = false)
    private Long currencyId;

    @Column(name = "CURRENCY_NAME", length = 45)
    private String name;

    @Column(name = "CURRENCY_CODE", length = 5, unique = true)
    private String code;

    @OneToMany(mappedBy = "bnRCurrency", fetch = FetchType.LAZY)
    private List<BnRCurrencyRate> bnRCurrencyRates;

}