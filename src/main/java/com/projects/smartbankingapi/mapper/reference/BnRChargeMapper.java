package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRChargeDto;
import com.projects.smartbankingapi.model.reference.BnRCharge;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRFeeTypeMapper.class, BnRCurrencyMapper.class})
public interface BnRChargeMapper {
    BnRCharge toEntity(BnRChargeDto bnRChargeDto);

    BnRChargeDto toDto(BnRCharge bnRCharge);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRCharge partialUpdate(BnRChargeDto bnRChargeDto, @MappingTarget BnRCharge bnRCharge);
}