package com.projects.smartbankingapi.mapper.transaction;

import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import com.projects.smartbankingapi.mapper.master.BnMAccountMapper;
import com.projects.smartbankingapi.mapper.reference.BnRTranTypeMapper;
import com.projects.smartbankingapi.model.transaction.BnTTran;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRTranTypeMapper.class, BnMAccountMapper.class})
public interface BnTTranMapper {
    @Mappings({
            @Mapping(target = "bnRTranType.tranTypeId", source = "tranTypeId"),
            @Mapping(target = "bnRStatus.statusId", source = "statusId"),
            @Mapping(target = "bnRBranch.branchId", source = "branchId")
    })
    BnTTran toEntity(BnTTranDto bnTTranDto);

    @Mappings({
            @Mapping(target = "tranTypeId", source = "bnRTranType.tranTypeId"),
            @Mapping(target = "statusId", source = "bnRStatus.statusId"),
            @Mapping(target = "branchId", source = "bnRBranch.branchId")
    })
    BnTTranDto toDto(BnTTran bnTTran);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnTTran partialUpdate(BnTTranDto bnTTranDto, @MappingTarget BnTTran bnTTran);

    @Mappings({
            @Mapping(target = "tranTypeId", source = "bnRTranType.tranTypeId"),
            @Mapping(target = "statusId", source = "bnRStatus.statusId"),
            @Mapping(target = "branchId", source = "bnRBranch.branchId")
    })
    List<BnTTranDto> entityListToDtoList(List<BnTTran> content);
}