package com.projects.smartbankingapi.mapper.master;

import com.projects.smartbankingapi.dto.master.BnMBeneficiaryDto;
import com.projects.smartbankingapi.mapper.reference.BnRBranchMapper;
import com.projects.smartbankingapi.model.master.BnMBeneficiary;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnMAccountMapper.class, BnRBranchMapper.class})
public interface BnMBeneficiaryMapper {
    BnMBeneficiary toEntity(BnMBeneficiaryDto bnMBeneficiaryDto);

    BnMBeneficiaryDto toDto(BnMBeneficiary bnMBeneficiary);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnMBeneficiary partialUpdate(BnMBeneficiaryDto bnMBeneficiaryDto, @MappingTarget BnMBeneficiary bnMBeneficiary);
}