package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRBranchDto;
import com.projects.smartbankingapi.model.reference.BnRBranch;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRBankMapper.class})
public interface BnRBranchMapper {

    @Mappings({
            @Mapping(target = "bnRBank.bankId",source = "bankId")
    })
    BnRBranch toEntity(BnRBranchDto bnRBranchDto);

    @Mappings({
            @Mapping(target = "bankId",source = "bnRBank.bankId")

    })
    BnRBranchDto toDto(BnRBranch bnRBranch);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRBranch partialUpdate(BnRBranchDto bnRBranchDto, @MappingTarget BnRBranch bnRBranch);

    @Mappings({
            @Mapping(target = "bankId",source = "bnRBank.bankId")

    })
    List<BnRBranchDto> entityListToDtoList(List<BnRBranch> branches);
}