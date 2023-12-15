package com.projects.smartbankingapi.model.reference;

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
@Table(name = "BN_R_FEE_TYPE", indexes = {
        @Index(name = "BN_R_FEE_TYPE_FEE_TYPE_ID_IDX", columnList = "FEE_TYPE_ID"),
})
public class BnRFeeType extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_FEE_TYPE", sequenceName = "BN_R_FEE_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_FEE_TYPE")
    @Column(name = "FEE_TYPE_ID", nullable = false)
    private Long feeTypeId;

    @Column(name = "FEE_TYPE_NAME", length = 45)
    private String name;

    @Column(name = "FEE_TYPE_DESC", length = 100)
    private String description;

}