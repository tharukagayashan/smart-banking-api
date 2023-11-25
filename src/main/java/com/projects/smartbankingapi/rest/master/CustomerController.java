package com.projects.smartbankingapi.rest.master;

import com.projects.smartbankingapi.dto.master.BnMCustomerDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.CustomerCreateReqDto;
import com.projects.smartbankingapi.service.master.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<BnMCustomerDto> createCustomer(@Valid @RequestBody CustomerCreateReqDto customerCreateReqDto) {
        ResponseEntity<BnMCustomerDto> response = customerService.createCustomer(customerCreateReqDto);
        return response;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<BnMCustomerDto> getCustomer(@RequestParam Long customerId) {
        ResponseEntity<BnMCustomerDto> response = customerService.getCustomer(customerId);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BnMCustomerDto>> getAllCustomers() {
        ResponseEntity<List<BnMCustomerDto>> response = customerService.getAllCustomers();
        return response;
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<BnMCustomerDto> updateCustomer(@RequestParam Long customerId, @Valid @RequestBody BnMCustomerDto bnMCustomerDto) {
        ResponseEntity<BnMCustomerDto> response = customerService.updateCustomer(customerId, bnMCustomerDto);
        return response;
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<BnMCustomerDto> deleteCustomer(@RequestParam Long customerId) {
        ResponseEntity<BnMCustomerDto> response = customerService.deleteCustomer(customerId);
        return response;
    }

    @GetMapping("/all/isActive/{isActive}")
    public ResponseEntity<List<BnMCustomerDto>> getAllCustomersByIsActive(@PathVariable String isActive) {
        ResponseEntity<List<BnMCustomerDto>> response = customerService.getAllCustomersByIsActive(isActive);
        return response;
    }

    @GetMapping("/table")
    public ResponseEntity<ApiResponseDto<List<BnMCustomerDto>>> getCustomersForTable(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer perPage,
            @RequestParam(required = false, defaultValue = "customerId") String sort,
            @RequestParam(required = false, defaultValue = "asc") String direction,
            @RequestParam(required = false, defaultValue = "") String search
    ){
        ResponseEntity<ApiResponseDto<List<BnMCustomerDto>>> response = customerService.getCustomersForTable(page, perPage, sort, direction, search);
        return response;
    }

}
