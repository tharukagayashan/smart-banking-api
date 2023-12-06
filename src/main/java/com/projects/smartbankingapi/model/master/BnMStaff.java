package com.projects.smartbankingapi.model.master;

import com.projects.smartbankingapi.audit.AuditModel;
import com.projects.smartbankingapi.model.reference.BnRBranch;
import com.projects.smartbankingapi.model.reference.BnRRole;
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
@Table(name = "BN_M_STAFF", indexes = {
        @Index(name = "BN_M_STAFF_STAFF_ID_IDX", columnList = "STAFF_ID"),
        @Index(name = "BN_M_STAFF_ROLE_ID_IDX", columnList = "ROLE_ID"),
        @Index(name = "BN_M_STAFF_BRANCH_ID_IDX", columnList = "BRANCH_ID"),
        @Index(name = "BN_M_STAFF_USERNAME_UNIQUE_IDX", columnList = "USERNAME")
})
public class BnMStaff extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_M_STAFF", sequenceName = "BN_M_STAFF_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "BN_M_STAFF", strategy = GenerationType.SEQUENCE)
    @Column(name = "STAFF_ID", nullable = false)
    private Long staffId;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "EMAIL", length = 70)
    private String email;

    @Column(name = "USERNAME", length = 30, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 200)
    private String password;

    @Column(name = "MOBILE_NO", length = 10)
    private String mobileNo;

    @Column(name = "ADDRESS", length = 80)
    private String address;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", nullable = false)
    private BnRRole bnRRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "BRANCH_ID", nullable = false)
    private BnRBranch bnRBranch;

}