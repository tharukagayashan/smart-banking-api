package com.projects.smartbankingapi.rest.master;

import com.projects.smartbankingapi.dto.master.BnMCustomerDto;
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

}
