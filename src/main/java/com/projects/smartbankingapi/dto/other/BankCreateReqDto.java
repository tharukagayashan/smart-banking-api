package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankCreateReqDto {
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;
    @NotNull
    @NotEmpty
    @NotBlank
    private String code;
}
