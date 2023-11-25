package com.projects.smartbankingapi.service.master;

import com.projects.smartbankingapi.dto.master.BnMCustomerDto;
import com.projects.smartbankingapi.dto.other.CustomerCreateReqDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<BnMCustomerDto> createCustomer(CustomerCreateReqDto customerCreateReqDto);

    ResponseEntity<BnMCustomerDto> getCustomer(Long customerId);

    ResponseEntity<List<BnMCustomerDto>> getAllCustomers();

    ResponseEntity<BnMCustomerDto> updateCustomer(Long customerId, BnMCustomerDto bnMCustomerDto);

    ResponseEntity<BnMCustomerDto> deleteCustomer(Long customerId);
}
