package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRChargeDto;
import com.projects.smartbankingapi.model.reference.BnRCharge;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRFeeTypeMapper.class, BnRCurrencyMapper.class})
public interface BnRChargeMapper {

    @Mappings({
            @Mapping(target = "bnRFeeType.feeTypeId", source = "feeTypeId"),
            @Mapping(target = "bnRCurrency.currencyId", source = "currencyId")
    })
    BnRCharge toEntity(BnRChargeDto bnRChargeDto);

    @Mappings({
            @Mapping(target = "feeTypeId", source = "bnRFeeType.feeTypeId"),
            @Mapping(target = "currencyId", source = "bnRCurrency.currencyId")
    })
    BnRChargeDto toDto(BnRCharge bnRCharge);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRCharge partialUpdate(BnRChargeDto bnRChargeDto, @MappingTarget BnRCharge bnRCharge);

    @Mappings({
            @Mapping(target = "feeTypeId", source = "bnRFeeType.feeTypeId"),
            @Mapping(target = "currencyId", source = "bnRCurrency.currencyId")
    })
    List<BnRChargeDto> entityListToDtoList(List<BnRCharge> charges);
}