package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRRoleDto;
import com.projects.smartbankingapi.model.reference.BnRRole;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRRoleMapper {
    BnRRole toEntity(BnRRoleDto bnRRoleDto);

    BnRRoleDto toDto(BnRRole bnRRole);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRRole partialUpdate(BnRRoleDto bnRRoleDto, @MappingTarget BnRRole bnRRole);

    List<BnRRoleDto> entityListToDtoList(List<BnRRole> roles);
}