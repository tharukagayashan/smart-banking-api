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
public class LoanPeriodCreateReqDto {
    @NotBlank
    @NotEmpty
    @NotNull
    private String name;
    @NotBlank
    @NotEmpty
    @NotNull
    private String description;
    @NotNull
    private Integer month;
}