package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerCreateReqDto {
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
}