package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRCurrencyDto;
import com.projects.smartbankingapi.model.reference.BnRCurrency;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRCurrencyMapper {
    BnRCurrency toEntity(BnRCurrencyDto bnRCurrencyDto);

    BnRCurrencyDto toDto(BnRCurrency bnRCurrency);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRCurrency partialUpdate(BnRCurrencyDto bnRCurrencyDto, @MappingTarget BnRCurrency bnRCurrency);

    List<BnRCurrencyDto> entityListToDtoList(List<BnRCurrency> currencies);
}