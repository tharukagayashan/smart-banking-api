package com.projects.smartbankingapi.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffRegisterReqDto {
    @NotBlank
    @NotEmpty
    @NotNull
    private String firstName;
    @NotBlank
    @NotEmpty
    @NotNull
    private String lastName;
    @NotBlank
    @NotEmpty
    @NotNull
    private String email;
    @NotBlank
    @NotEmpty
    @NotNull
    private String username;
    @NotBlank
    @NotEmpty
    @NotNull
    private String password;
    @NotBlank
    @NotEmpty
    @NotNull
    private String mobileNo;
    @NotBlank
    @NotEmpty
    @NotNull
    private String address;
    @NotNull
    private Long roleId;
    @NotNull
    private Long branchId;
}