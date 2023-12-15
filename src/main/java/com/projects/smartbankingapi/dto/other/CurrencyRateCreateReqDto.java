package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyRateCreateReqDto {
    @Min(0)
    private Float sellingRate;
    @Min(0)
    private Float buyingRate;
    @Min(0)
    private Float middleRate;
    @NotNull
    private LocalDate publicationDate;
    @NotNull
    private Long currencyId;
}