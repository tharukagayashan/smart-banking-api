package com.projects.smartbankingapi.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnRCurrencyRateDto {
    private Long currencyRateId;
    private Float sellingRate;
    private Float buyingRate;
    private Float middleRate;
    private LocalDate publicationDate;

    private Long currencyId;

    private BnRCurrencyDto bnRCurrency;

}