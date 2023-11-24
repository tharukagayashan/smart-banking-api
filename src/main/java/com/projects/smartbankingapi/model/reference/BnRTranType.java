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
@Table(name = "BN_R_TRAN_TYPE", indexes = {
        @Index(name = "BN_R_TRAN_TYPE_TRAN_TYPE_ID_IDX", columnList = "TRAN_TYPE_ID"),
        @Index(name = "BN_R_TRAN_TYPE_TRAN_TYPE_CODE_IDX", columnList = "TRAN_TYPE_CODE", unique = true)
})
public class BnRTranType extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_TRAN_TYPE", sequenceName = "BN_R_TRAN_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_TRAN_TYPE_SEQ")
    @Column(name = "TRAN_TYPE_ID", nullable = false)
    private Long tranTypeId;

    @Column(name = "TRAN_TYPE_NAME", length = 45)
    private String name;

    @Column(name = "TRAN_TYPE_CODE", length = 5, unique = true)
    private String code;

}