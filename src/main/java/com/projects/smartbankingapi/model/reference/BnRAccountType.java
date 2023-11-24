package com.projects.smartbankingapi.model.reference;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_R_ACCOUNT_TYPE",indexes = {
        @Index(name = "BN_R_ACCOUNT_TYPE_ACCOUNT_TYPE_ID_IDX", columnList = "ACCOUNT_TYPE_ID"),
        @Index(name = "BN_R_ACCOUNT_TYPE_ACCOUNT_TYPE_CODE_IDX", columnList = "ACCOUNT_TYPE_CODE", unique = true)
})
public class BnRAccountType extends AuditModel {
    @Id
    @SequenceGenerator(name = "BN_R_ACCOUNT_TYPE", sequenceName = "BN_R_ACCOUNT_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_ACCOUNT_TYPE_SEQ")
    @Column(name = "ACCOUNT_TYPE_ID", nullable = false)
    private Long accountTypeId;

    @Column(name = "ACCOUNT_TYPE_NAME", length = 50)
    private String name;

    @Column(name = "ACCOUNT_TYPE_CODE", length = 5, unique = true)
    private String code;

}