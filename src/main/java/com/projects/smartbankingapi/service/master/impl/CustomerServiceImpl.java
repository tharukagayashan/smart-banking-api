package com.projects.smartbankingapi.service.master.impl;

import com.projects.smartbankingapi.constant.HardCodeConstant;
import com.projects.smartbankingapi.dao.master.BnMCustomerRepository;
import com.projects.smartbankingapi.dto.master.BnMCustomerDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.miscellaneous.PaginationDto;
import com.projects.smartbankingapi.dto.other.CustomerCreateReqDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.master.BnMCustomerMapper;
import com.projects.smartbankingapi.model.master.BnMCustomer;
import com.projects.smartbankingapi.service.master.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final BnMCustomerRepository customerRepository;
    private final BnMCustomerMapper customerMapper;

    public CustomerServiceImpl(BnMCustomerRepository customerRepository, BnMCustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    private static BnMCustomer getBnMCustomer(CustomerCreateReqDto customerCreateReqDto) {
        BnMCustomer customer = new BnMCustomer();
        customer.setFirstName(customerCreateReqDto.getFirstName());
        customer.setLastName(customerCreateReqDto.getLastName());
        customer.setDob(customerCreateReqDto.getDob());
        customer.setAddress(customerCreateReqDto.getAddress());
        customer.setEmail(customerCreateReqDto.getEmail());
        customer.setMobileNo(customerCreateReqDto.getMobileNo());
        customer.setNic(customerCreateReqDto.getNic());
        customer.setGender(customerCreateReqDto.getGender());
        customer.setIsActive(HardCodeConstant.ACTIVE);
        return customer;
    }

    private static BnMCustomer getBnMCustomer(BnMCustomer entity, BnMCustomerDto bnMCustomerDto) {
        entity.setCustomerId(bnMCustomerDto.getCustomerId());
        entity.setFirstName(bnMCustomerDto.getFirstName());
        entity.setLastName(bnMCustomerDto.getLastName());
        entity.setDob(bnMCustomerDto.getDob());
        entity.setAddress(bnMCustomerDto.getAddress());
        entity.setEmail(bnMCustomerDto.getEmail());
        entity.setMobileNo(bnMCustomerDto.getMobileNo());
        entity.setNic(bnMCustomerDto.getNic());
        entity.setGender(bnMCustomerDto.getGender());
        entity.setIsActive(bnMCustomerDto.getIsActive());
        return entity;
    }

    @Override
    public ResponseEntity<BnMCustomerDto> createCustomer(CustomerCreateReqDto customerCreateReqDto) {
        try {
            // Check if customer already exists with the given NIC
            Optional<BnMCustomer> optCustomer = customerRepository.findByNic(customerCreateReqDto.getNic());
            if (optCustomer.isPresent()) {
                throw new BadRequestAlertException("Customer already exists with the given NIC", "BnMCustomer", "error");
            }

            BnMCustomer customer = getBnMCustomer(customerCreateReqDto);
            customer = customerRepository.save(customer);
            if (customer.getCustomerId() == null) {
                throw new BadRequestAlertException("Customer creation failed", "BnMCustomer", "error");
            } else {
                BnMCustomerDto customerDto = customerMapper.toDto(customer);
                return ResponseEntity.ok(customerDto);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating customer: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMCustomer", "error");
        }
    }

    @Override
    public ResponseEntity<BnMCustomerDto> getCustomer(Long customerId) {
        try {
            if (customerId == null) {
                throw new BadRequestAlertException("Customer id is required", "BnMCustomer", "error");
            } else {
                Optional<BnMCustomer> optCustomer = customerRepository.findById(customerId);
                if (!optCustomer.isPresent()) {
                    throw new BadRequestAlertException("Customer not found", "BnMCustomer", "error");
                } else {
                    BnMCustomerDto customerDto = customerMapper.toDto(optCustomer.get());
                    return ResponseEntity.ok(customerDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while getting customer: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMCustomer", "error");
        }
    }

    @Override
    public ResponseEntity<List<BnMCustomerDto>> getAllCustomers() {
        try {
            List<BnMCustomer> customers = customerRepository.findAll();
            if (customers.isEmpty()) {
                throw new BadRequestAlertException("No customers found", "BnMCustomer", "error");
            } else {
                List<BnMCustomerDto> customerDtoList = customerMapper.entityListToDtoList(customers);
                return ResponseEntity.ok(customerDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while getting all customers: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMCustomer", "error");
        }
    }

    @Override
    public ResponseEntity<BnMCustomerDto> updateCustomer(Long customerId, BnMCustomerDto bnMCustomerDto) {
        try {
            if (!Objects.equals(customerId, bnMCustomerDto.getCustomerId())) {
                throw new BadRequestAlertException("Customer id mismatch", "BnMCustomer", "error");
            } else {
                ResponseEntity<BnMCustomerDto> customerRes = getCustomer(customerId);
                if (customerRes.getStatusCode() != HttpStatus.OK) {
                    throw new BadRequestAlertException("Customer not found", "BnMCustomer", "error");
                } else {
                    BnMCustomer updatedCustomer = getBnMCustomer(customerMapper.toEntity(customerRes.getBody()), bnMCustomerDto);
                    updatedCustomer = customerRepository.save(updatedCustomer);
                    BnMCustomerDto updatedCustomerDto = customerMapper.toDto(updatedCustomer);
                    return ResponseEntity.ok(updatedCustomerDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while updating customer: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMCustomer", "error");
        }
    }

    @Override
    public ResponseEntity<BnMCustomerDto> deleteCustomer(Long customerId) {
        try {
            if (customerId == null) {
                throw new BadRequestAlertException("Customer id is required", "BnMCustomer", "error");
            } else {
                ResponseEntity<BnMCustomerDto> customerRes = getCustomer(customerId);
                if (customerRes.getStatusCode() != HttpStatus.OK) {
                    throw new BadRequestAlertException("Customer not found", "BnMCustomer", "error");
                } else {
                    BnMCustomer customer = customerMapper.toEntity(customerRes.getBody());
                    customer.setIsActive(HardCodeConstant.INACTIVE);
                    customer = customerRepository.save(customer);
                    if (customer.getIsActive() != HardCodeConstant.INACTIVE) {
                        throw new BadRequestAlertException("Customer deletion failed", "BnMCustomer", "error");
                    } else {
                        BnMCustomerDto customerDto = customerMapper.toDto(customer);
                        return ResponseEntity.ok(customerDto);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting customer: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMCustomer", "error");
        }
    }

    @Override
    public ResponseEntity<List<BnMCustomerDto>> getAllCustomersByIsActive(String isActive) {
        try {
            if (isActive == null) {
                throw new BadRequestAlertException("isActive is required", "BnMCustomer", "error");
            } else {
                boolean flag;
                if (isActive.equals(HardCodeConstant.ACTIVE.toString())) {
                    flag = true;
                } else if (isActive.equals(HardCodeConstant.INACTIVE.toString())) {
                    flag = false;
                } else {
                    throw new BadRequestAlertException("isActive should be true or false", "BnMCustomer", "error");
                }
                List<BnMCustomer> customers = customerRepository.findAllByIsActive(flag);
                if (customers.isEmpty()) {
                    throw new BadRequestAlertException("No customers found", "BnMCustomer", "error");
                } else {
                    List<BnMCustomerDto> customerDtoList = customerMapper.entityListToDtoList(customers);
                    return ResponseEntity.ok(customerDtoList);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while getting all customers by isActive: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMCustomer", "error");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<BnMCustomerDto>>> getCustomersForTable(Integer page, Integer perPage, String sort, String direction, String search) {
        try {
            Page<BnMCustomer> dbData = null;
            if (direction.equalsIgnoreCase("")) {
                dbData = customerRepository.findAllForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort)));
            } else {
                dbData = customerRepository.findAllForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort)));
            }

            ApiResponseDto<List<BnMCustomerDto>> response = new ApiResponseDto<>();
            PaginationDto pagination = new PaginationDto();
            pagination.setTotal(dbData.getTotalElements());
            pagination.setPerPage(perPage);
            pagination.setCurrentPage(page);
            pagination.setFrom((page * perPage) + dbData.getNumberOfElements());
            pagination.setTo((page * perPage) + dbData.getNumberOfElements());
            response.setPagination(pagination);
            response.setResult(customerMapper.entityListToDtoList(dbData.getContent()));
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error occurred while getting customers for table: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMCustomer", "error");
        }
    }
}