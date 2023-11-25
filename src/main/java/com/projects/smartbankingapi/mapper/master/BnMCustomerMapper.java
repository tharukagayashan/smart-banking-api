package com.projects.smartbankingapi.mapper.master;

import com.projects.smartbankingapi.dto.master.BnMCustomerDto;
import com.projects.smartbankingapi.model.master.BnMCustomer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnMCustomerMapper {
    BnMCustomer toEntity(BnMCustomerDto bnMCustomerDto);

    BnMCustomerDto toDto(BnMCustomer bnMCustomer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnMCustomer partialUpdate(BnMCustomerDto bnMCustomerDto, @MappingTarget BnMCustomer bnMCustomer);
}