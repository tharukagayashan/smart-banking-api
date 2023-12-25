package com.projects.smartbankingapi.model.reference;

import com.projects.smartbankingapi.audit.AuditModel;
import com.projects.smartbankingapi.model.transaction.BnTLoanTran;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BN_R_LOAN_PAY_TYPE")
public class BnRLoanPayType extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_LOAN_PAY_TYPE", sequenceName = "BN_R_LOAN_PAY_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "BN_R_LOAN_PAY_TYPE", strategy = GenerationType.SEQUENCE)
    @Column(name = "LOAN_PAY_TYPE_ID", nullable = false)
    private Long loanPayTypeId;

    @Column(name = "PAY_TYPE", length = 45)
    private String payType;

    @Column(name = "PAY_TYPE_CODE", length = 10, unique = true)
    private String payTypeCode;

    @OneToMany(mappedBy = "bnRLoanPayType", fetch = FetchType.LAZY)
    private List<BnTLoanTran> bnTLoanTrans;

}
