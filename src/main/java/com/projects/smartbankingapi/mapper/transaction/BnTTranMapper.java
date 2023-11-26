package com.projects.smartbankingapi.mapper.transaction;

import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import com.projects.smartbankingapi.mapper.master.BnMAccountMapper;
import com.projects.smartbankingapi.mapper.reference.BnRTranTypeMapper;
import com.projects.smartbankingapi.model.transaction.BnTTran;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRTranTypeMapper.class, BnMAccountMapper.class})
public interface BnTTranMapper {
    @Mappings({
            @Mapping(target = "bnRTranType.tranTypeId", source = "tranTypeId")
    })
    BnTTran toEntity(BnTTranDto bnTTranDto);

    @Mappings({
            @Mapping(target = "tranTypeId", source = "bnRTranType.tranTypeId")
    })
    BnTTranDto toDto(BnTTran bnTTran);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnTTran partialUpdate(BnTTranDto bnTTranDto, @MappingTarget BnTTran bnTTran);
}