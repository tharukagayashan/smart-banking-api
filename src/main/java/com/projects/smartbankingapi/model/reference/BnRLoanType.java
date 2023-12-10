package com.projects.smartbankingapi.model.reference;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_R_LOAN_TYPE", indexes = {
        @Index(name = "BN_R_LOAN_TYPE_LOAN_TYPE_ID_IDX", columnList = "LOAN_TYPE_ID"),
        @Index(name = "BN_R_LOAN_TYPE_LOAN_TYPE_CODE_IDX", columnList = "LOAN_TYPE_CODE", unique = true)
})
public class BnRLoanType extends AuditModel {
    @Id
    @SequenceGenerator(name = "BN_R_LOAN_TYPE", sequenceName = "BN_R_LOAN_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_LOAN_TYPE_SEQ")
    @Column(name = "LOAN_TYPE_ID", nullable = false)
    private Long loanTypeId;

    @Column(name = "LOAN_TYPE_NAME", length = 50)
    private String name;

    @Column(name = "LOAN_TYPE_CODE", length = 5, unique = true)
    private String code;

    @Column(name = "LOAN_TYPE_DESC", length = 100)
    private String description;

    @OneToMany(mappedBy = "bnRLoanType", fetch = FetchType.LAZY)
    private List<BnRLoanProduct> bnRLoanProducts;

}