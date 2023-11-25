package com.projects.smartbankingapi.model.master;

import com.projects.smartbankingapi.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BN_M_CUSTOMER", indexes = {
        @Index(name = "BN_M_CUSTOMER_CUSTOMER_ID_IDX", columnList = "CUSTOMER_ID"),
        @Index(name = "BN_M_CUSTOMER_DOB_UNIQUE_IDX", columnList = "NIC", unique = true)
})
public class BnMCustomer extends AuditModel {

    @Id
    @SequenceGenerator(name = "BN_M_CUSTOMER", sequenceName = "BN_M_CUSTOMER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BN_M_CUSTOMER_SEQ")
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "ADDRESS", length = 200)
    private String address;

    @Column(name = "EMAIL", length = 60)
    private String email;

    @Column(name = "MOBILE_NO", length = 10)
    private String mobileNo;

    @Column(name = "NIC", length = 12, unique = true)
    private String nic;

    @Column(name = "GENDER", length = 1)
    private String gender;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

}