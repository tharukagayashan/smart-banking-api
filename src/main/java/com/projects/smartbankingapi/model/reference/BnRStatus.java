package com.projects.smartbankingapi.model.reference;


import com.projects.smartbankingapi.audit.AuditModel;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BN_R_STATUS", indexes = {
        @Index(name = "BN_R_STATUS_STATUS_ID_IDX", columnList = "STATUS_ID"),
        @Index(name = "BN_R_STATUS_STATUS_CODE_IDX", columnList = "STATUS_CODE", unique = true)
})
public class BnRStatus extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_STATUS", sequenceName = "BN_R_STATUS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_R_STATUS")
    @Column(name = "STATUS_ID", nullable = false)
    private Long statusId;

    @Column(name = "STATUS_NAME", length = 45)
    private String name;

    @Column(name = "STATUS_CODE", length = 5, unique = true)
    private String code;

    @Column(name = "STATUS_TYPE", length = 45)
    private String type;

}