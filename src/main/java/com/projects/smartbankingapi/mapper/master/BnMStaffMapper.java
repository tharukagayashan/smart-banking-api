package com.projects.smartbankingapi.mapper.master;

import com.projects.smartbankingapi.dto.master.BnMStaffDto;
import com.projects.smartbankingapi.mapper.reference.BnRBranchMapper;
import com.projects.smartbankingapi.mapper.reference.BnRRoleMapper;
import com.projects.smartbankingapi.model.master.BnMStaff;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRRoleMapper.class, BnRBranchMapper.class})
public interface BnMStaffMapper {

    @Mappings({
            @Mapping(target = "bnRBranch.branchId", source = "branchId"),
            @Mapping(target = "bnRRole.roleId", source = "roleId"),
    })
    BnMStaff toEntity(BnMStaffDto bnMStaffDto);

    @Mappings({
            @Mapping(target = "roleId", source = "bnRRole.roleId"),
            @Mapping(target = "branchId", source = "bnRBranch.branchId")
    })
    BnMStaffDto toDto(BnMStaff bnMStaff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnMStaff partialUpdate(BnMStaffDto bnMStaffDto, @MappingTarget BnMStaff bnMStaff);

    @Mappings({
            @Mapping(target = "roleId", source = "bnRRole.roleId"),
            @Mapping(target = "branchId", source = "bnRBranch.branchId")
    })
    List<BnMStaffDto> entityListToDtoList(List<BnMStaff> bnMStaffs);
}