package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRCurrencyRateDto;
import com.projects.smartbankingapi.model.reference.BnRCurrencyRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRCurrencyRateMapper {

    @Mappings({
            @Mapping(target = "bnRCurrency.currencyId", source = "currencyId")
    })
    BnRCurrencyRate toEntity(BnRCurrencyRateDto bnRCurrencyRateDto);

    @Mappings({
            @Mapping(target = "currencyId", source = "bnRCurrency.currencyId")
    })
    BnRCurrencyRateDto toDto(BnRCurrencyRate bnRCurrencyRate);

    @Mappings({
            @Mapping(target = "currencyId", source = "bnRCurrency.currencyId")
    })
    List<BnRCurrencyRateDto> entityListToDtoList(List<BnRCurrencyRate> bnRCurrencyRates);

}
