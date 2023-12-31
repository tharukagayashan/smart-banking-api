package com.projects.smartbankingapi.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Long customerId;
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotNull
    @NotEmpty
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDate dob;
    @NotNull
    @NotEmpty
    @NotBlank
    private String address;
    @NotNull
    @NotEmpty
    @NotBlank
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    private String mobileNo;
    @NotNull
    @NotEmpty
    @NotBlank
    private String nic;
    @NotNull
    @NotEmpty
    @NotBlank
    private String gender;
    @NotNull
    private Boolean isActive;
}