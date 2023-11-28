package com.projects.smartbankingapi.model.reference;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_R_LOAN_PERIOD")
public class BnRLoanPeriod extends AuditModel {
    @Id
    @SequenceGenerator(name = "BN_R_LOAN_PERIOD", sequenceName = "BN_R_LOAN_PERIOD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_LOAN_PERIOD_SEQ")
    @Column(name = "LOAN_PERIOD_ID", nullable = false)
    private Long periodId;

    @Column(name = "LOAN_PERIOD_NAME", length = 45)
    private String name;

    @Column(name = "LOAN_PERIOD_DESC", length = 100)
    private String description;

    @Column(name = "MONTH")
    private Integer month;

    @OneToMany(mappedBy = "bnRLoanPeriod", fetch = FetchType.LAZY)
    private List<BnRLoanProduct> bnRLoanProducts;

}
