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
@Table(name = "BN_R_INT_RATE", indexes = {
        @Index(name = "BN_R_INT_RATE_INT_RATE_ID_IDX", columnList = "INT_RATE_ID")
})
public class BnRIntRate extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_INT_RATE", sequenceName = "BN_R_INT_RATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_INT_RATE")
    @Column(name = "INT_RATE_ID", nullable = false)
    private Long intRateId;

    @Column(name = "INT_RATE_NAME", length = 45)
    private String name;

    @Column(name = "INT_RATE_DESC", length = 100)
    private String description;

    @Column(name = "RATE", nullable = false)
    private Float rate;

    @OneToMany(mappedBy = "bnRIntRate", fetch = FetchType.LAZY)
    private List<BnRLoanProduct> bnRLoanProducts;

}