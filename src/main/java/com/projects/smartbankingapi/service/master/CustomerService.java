package com.projects.smartbankingapi.service.master;

import com.projects.smartbankingapi.dto.master.BnMCustomerDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.CustomerCreateReqDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<BnMCustomerDto> createCustomer(CustomerCreateReqDto customerCreateReqDto);

    ResponseEntity<BnMCustomerDto> getCustomer(Long customerId);

    ResponseEntity<List<BnMCustomerDto>> getAllCustomers();

    ResponseEntity<BnMCustomerDto> updateCustomer(Long customerId, BnMCustomerDto bnMCustomerDto);

    ResponseEntity<BnMCustomerDto> deleteCustomer(Long customerId);

    ResponseEntity<List<BnMCustomerDto>> getAllCustomersByIsActive(String isActive);

    ResponseEntity<ApiResponseDto<List<BnMCustomerDto>>> getCustomersForTable(Integer page, Integer perPage, String sort, String direction, String search);
}
