package com.projects.smartbankingapi.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.master.BnMCustomer}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnMCustomerDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long customerId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private String email;
    private String mobileNo;
    private String nic;
    private String gender;
    private Boolean isActive;
}