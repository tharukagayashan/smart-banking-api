package com.projects.smartbankingapi.rest.reference;

import com.projects.smartbankingapi.dto.reference.*;
import com.projects.smartbankingapi.service.reference.ReferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reference")
public class ReferenceController {

    private final ReferenceService referenceService;

    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @GetMapping("/account-type/fdd")
    public ResponseEntity<List<BnRAccountTypeDto>> getAllAccountTypes() {
        return referenceService.getAllAccountTypes();
    }

    @GetMapping("/bank/fdd")
    public ResponseEntity<List<BnRBankDto>> getAllBanks() {
        return referenceService.getAllBanks();
    }

    @GetMapping("/branch/fdd")
    public ResponseEntity<List<BnRBranchDto>> getAllBranches() {
        return referenceService.getAllBranches();
    }

    @GetMapping("/branch/fdd/{bankId}")
    public ResponseEntity<List<BnRBranchDto>> getAllBranchesByBankId(@PathVariable Long bankId) {
        return referenceService.getAllBranchesByBankId(bankId);
    }

    @GetMapping("/charge/fdd")
    public ResponseEntity<List<BnRChargeDto>> getAllCharges() {
        return referenceService.getAllCharges();
    }

    @GetMapping("/currency/fdd")
    public ResponseEntity<List<BnRCurrencyDto>> getAllCurrencies() {
        return referenceService.getAllCurrencies();
    }

    @GetMapping("/fee-type/fdd")
    public ResponseEntity<List<BnRFeeTypeDto>> getAllFeeTypes() {
        return referenceService.getAllFeeTypes();
    }

    @GetMapping("/int-rate/fdd")
    public ResponseEntity<List<BnRIntRateDto>> getAllIntRates() {
        return referenceService.getAllIntRates();
    }

    @GetMapping("/loan-period/fdd")
    public ResponseEntity<List<BnRLoanPeriodDto>> getAllLoanPeriods() {
        return referenceService.getAllLoanPeriods();
    }

    @GetMapping("/loan-product/fdd")
    public ResponseEntity<List<BnRLoanProductDto>> getAllLoanProducts() {
        return referenceService.getAllLoanProducts();
    }

    @GetMapping("/loan-type/fdd")
    public ResponseEntity<List<BnRLoanTypeDto>> getAllLoanTypes() {
        return referenceService.getAllLoanTypes();
    }

    @GetMapping("/status/fdd")
    public ResponseEntity<List<BnRStatusDto>> getAllStatuses() {
        return referenceService.getAllStatuses();
    }

    @GetMapping("/tran-type/fdd")
    public ResponseEntity<List<BnRTranTypeDto>> getAllTranTypes() {
        return referenceService.getAllTranTypes();
    }

}
