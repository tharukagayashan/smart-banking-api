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
@Table(name = "BN_R_BANK", indexes = {
        @Index(name = "BN_R_BANK_BANK_ID_IDX", columnList = "BANK_ID"),
        @Index(name = "BN_R_BANK_BANK_CODE_IDX", columnList = "BANK_CODE", unique = true)
})
public class BnRBank extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_BANK", sequenceName = "BN_R_BANK_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "BN_R_BANK")
    @Column(name = "BANK_ID", nullable = false)
    private Long bankId;

    @Column(name = "BANK_NAME", length = 45)
    private String name;

    @Column(name = "BANK_CODE", length = 5, unique = true)
    private String code;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @OneToMany(mappedBy = "bnRBank", fetch = FetchType.LAZY)
    private List<BnRBranch> bnRBranches;

}