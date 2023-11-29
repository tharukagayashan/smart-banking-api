package com.projects.smartbankingapi.rest.reference;

import com.projects.smartbankingapi.dto.other.*;
import com.projects.smartbankingapi.dto.reference.*;
import com.projects.smartbankingapi.service.reference.ReferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/reference")
public class ReferenceController {

    private final ReferenceService referenceService;

    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    /* GET ALL APIs (FDD) */

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

    @GetMapping("/product-type/filter")
    public ResponseEntity<List<BnRLoanProductDto>> getAllLoanProductsByFilter(
            @RequestParam(required = false) Long loanTypeId,
            @RequestParam(required = false) Long intRateId,
            @RequestParam(required = false) Long periodId
    ) {
        return referenceService.getAllLoanProductsByFilter(loanTypeId, intRateId, periodId);
    }

    /* POST APIs */

    @PostMapping("/account-type")
    public ResponseEntity<BnRAccountTypeDto> createAccountType(@Valid @RequestBody AccountTypeCreateReqDto accountTypeCreateReqDto) {
        return referenceService.createAccountType(accountTypeCreateReqDto);
    }

    @PostMapping("/bank")
    public ResponseEntity<BnRBankDto> createBank(@Valid @RequestBody BankCreateReqDto bankCreateReqDto) {
        return referenceService.createBank(bankCreateReqDto);
    }

    @PostMapping("/branch")
    public ResponseEntity<BnRBranchDto> createBranch(@Valid @RequestBody BranchCreateReqDto branchCreateReqDto) {
        return referenceService.createBranch(branchCreateReqDto);
    }

    @PostMapping("/charge")
    public ResponseEntity<BnRChargeDto> createCharge(@Valid @RequestBody ChargeCreateReqDto chargeCreateReqDto) {
        return referenceService.createCharge(chargeCreateReqDto);
    }

    @PostMapping("/currency")
    public ResponseEntity<BnRCurrencyDto> createCurrency(@Valid @RequestBody CurrencyCreateReqDto currencyCreateReqDto) {
        return referenceService.createCurrency(currencyCreateReqDto);
    }

}
