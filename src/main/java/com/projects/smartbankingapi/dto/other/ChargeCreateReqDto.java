package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChargeCreateReqDto {
    @NotNull
    @NotEmpty
    @NotBlank
    private String description;
    @Min(0)
    private Float amount;
    @NotNull
    private LocalDate effectiveDate;
    @NotNull
    private LocalDate expirationDate;

    @NotNull
    private Long feeTypeId;
    @NotNull
    private Long currencyId;
}
