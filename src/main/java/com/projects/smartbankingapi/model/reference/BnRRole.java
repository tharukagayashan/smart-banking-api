package com.projects.smartbankingapi.model.reference;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_R_ROLE")
public class BnRRole extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_R_ROLE", sequenceName = "BN_R_ROLE_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "BN_R_ROLE", strategy = GenerationType.SEQUENCE)
    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

    @Column(name = "NAME", length = 40)
    private String roleName;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

}
