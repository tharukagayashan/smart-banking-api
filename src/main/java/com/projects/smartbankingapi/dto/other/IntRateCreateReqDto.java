package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IntRateCreateReqDto {
    @NotBlank
    @NotEmpty
    @NotBlank
    private String name;
    @NotBlank
    @NotEmpty
    @NotBlank
    private String description;
    @Min(0)
    private Float rate;
}