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

    @GetMapping("/role/fdd")
    public ResponseEntity<List<BnRRoleDto>> getAllRoles() {
        return referenceService.getAllRoles();
    }

    @GetMapping("/currency-rate/fdd")
    public ResponseEntity<List<BnRCurrencyRateDto>> getAllCurrencyRates() {
        return referenceService.getAllCurrencyRates();
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

    @PostMapping("/fee-type")
    public ResponseEntity<BnRFeeTypeDto> createFeeType(@Valid @RequestBody FeeTypeCreateReqDto feeTypeCreateReqDto) {
        return referenceService.createFeeType(feeTypeCreateReqDto);
    }

    @PostMapping("/int-rate")
    public ResponseEntity<BnRIntRateDto> createIntRate(@Valid @RequestBody IntRateCreateReqDto intRateCreateReqDto) {
        return referenceService.createIntRate(intRateCreateReqDto);
    }

    @PostMapping("/loan-period")
    public ResponseEntity<BnRLoanPeriodDto> createLoanPeriod(@Valid @RequestBody LoanPeriodCreateReqDto loanPeriodCreateReqDto) {
        return referenceService.createLoanPeriod(loanPeriodCreateReqDto);
    }

    @PostMapping("/loan-product")
    public ResponseEntity<BnRLoanProductDto> createLoanProduct(@Valid @RequestBody LoanProductCreateReqDto loanProductCreateReqDto) {
        return referenceService.createLoanProduct(loanProductCreateReqDto);
    }

    @PostMapping("/loan-type")
    public ResponseEntity<BnRLoanTypeDto> createLoanType(@Valid @RequestBody LoanTypeCreateReqDto loanTypeCreateReqDto) {
        return referenceService.createLoanType(loanTypeCreateReqDto);
    }

    @PostMapping("/status")
    public ResponseEntity<BnRStatusDto> createStatus(@Valid @RequestBody StatusCreateReqDto statusCreateReqDto) {
        return referenceService.createStatus(statusCreateReqDto);
    }

    @PostMapping("/tran-type")
    public ResponseEntity<BnRTranTypeDto> createTranType(@Valid @RequestBody TranTypeCreateReqDto tranTypeCreateReqDto) {
        return referenceService.createTranType(tranTypeCreateReqDto);
    }

    @PostMapping("/role")
    public ResponseEntity<BnRRoleDto> createRole(@Valid @RequestBody RoleCreateReqDto roleCreateReqDto) {
        return referenceService.createRole(roleCreateReqDto);
    }

    @PostMapping("/currency-rate")
    public ResponseEntity<BnRCurrencyRateDto> createCurrencyRate(@Valid @RequestBody CurrencyRateCreateReqDto currencyRateCreateReqDto) {
        return referenceService.createCurrencyRate(currencyRateCreateReqDto);
    }

    /*** GET BY ID APIs (FDD) ***/

    @GetMapping("/account-type/{id}")
    public ResponseEntity<BnRAccountTypeDto> getAccountTypeById(@PathVariable Long id) {
        return referenceService.getAccountTypeById(id);
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<BnRBankDto> getBankById(@PathVariable Long id) {
        return referenceService.getBankById(id);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<BnRBranchDto> getBranchById(@PathVariable Long id) {
        return referenceService.getBranchById(id);
    }

    @GetMapping("/charge/{id}")
    public ResponseEntity<BnRChargeDto> getChargeById(@PathVariable Long id) {
        return referenceService.getChargeById(id);
    }

    @GetMapping("/currency/{id}")
    public ResponseEntity<BnRCurrencyDto> getCurrencyById(@PathVariable Long id) {
        return referenceService.getCurrencyById(id);
    }

    @GetMapping("/fee-type/{id}")
    public ResponseEntity<BnRFeeTypeDto> getFeeTypeById(@PathVariable Long id) {
        return referenceService.getFeeTypeById(id);
    }

    @GetMapping("/int-rate/{id}")
    public ResponseEntity<BnRIntRateDto> getIntRateById(@PathVariable Long id) {
        return referenceService.getIntRateById(id);
    }

    @GetMapping("/loan-period/{id}")
    public ResponseEntity<BnRLoanPeriodDto> getLoanPeriodById(@PathVariable Long id) {
        return referenceService.getLoanPeriodById(id);
    }

    @GetMapping("/loan-product/{id}")
    public ResponseEntity<BnRLoanProductDto> getLoanProductById(@PathVariable Long id) {
        return referenceService.getLoanProductById(id);
    }

    @GetMapping("/loan-type/{id}")
    public ResponseEntity<BnRLoanTypeDto> getLoanTypeById(@PathVariable Long id) {
        return referenceService.getLoanTypeById(id);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<BnRStatusDto> getStatusById(@PathVariable Long id) {
        return referenceService.getStatusById(id);
    }

    @GetMapping("/tran-type/{id}")
    public ResponseEntity<BnRTranTypeDto> getTranTypeById(@PathVariable Long id) {
        return referenceService.getTranTypeById(id);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<BnRRoleDto> getRoleById(@PathVariable Long id) {
        return referenceService.getRoleById(id);
    }


    @GetMapping("/currency-rate/{id}")
    public ResponseEntity<BnRCurrencyRateDto> getCurrencyRateById(@PathVariable Long id) {
        return referenceService.getCurrencyRateById(id);
    }


    /*** PUT APIs ***/
    @PutMapping("/account-type/{id}")
    public ResponseEntity<BnRAccountTypeDto> updateAccountType(@PathVariable Long id, @RequestBody BnRAccountTypeDto bnRAccountTypeDto) {
        return referenceService.updateAccountType(id, bnRAccountTypeDto);
    }

    @PutMapping("/bank/{id}")
    public ResponseEntity<BnRBankDto> updateBank(@PathVariable Long id, @RequestBody BnRBankDto bnRBankDto) {
        return referenceService.updateBank(id, bnRBankDto);
    }

    @PutMapping("/branch/{id}")
    public ResponseEntity<BnRBranchDto> updateBranch(@PathVariable Long id, @RequestBody BnRBranchDto bnRBranchDto) {
        return referenceService.updateBranch(id, bnRBranchDto);
    }

    @PutMapping("/charge/{id}")
    public ResponseEntity<BnRChargeDto> updateCharge(@PathVariable Long id, @RequestBody BnRChargeDto bnRChargeDto) {
        return referenceService.updateCharge(id, bnRChargeDto);
    }

    @PutMapping("/currency/{id}")
    public ResponseEntity<BnRCurrencyDto> updateCurrency(@PathVariable Long id, @RequestBody BnRCurrencyDto bnRCurrencyDto) {
        return referenceService.updateCurrency(id, bnRCurrencyDto);
    }

    @PutMapping("/fee-type/{id}")
    public ResponseEntity<BnRFeeTypeDto> updateFeeType(@PathVariable Long id, @RequestBody BnRFeeTypeDto bnRFeeTypeDto) {
        return referenceService.updateFeeType(id, bnRFeeTypeDto);
    }

    @PutMapping("/int-rate/{id}")
    public ResponseEntity<BnRIntRateDto> updateIntRate(@PathVariable Long id, @RequestBody BnRIntRateDto bnRIntRateDto) {
        return referenceService.updateIntRate(id, bnRIntRateDto);
    }

    @PutMapping("/loan-period/{id}")
    public ResponseEntity<BnRLoanPeriodDto> updateLoanPeriod(@PathVariable Long id, @RequestBody BnRLoanPeriodDto bnRLoanPeriodDto) {
        return referenceService.updateLoanPeriod(id, bnRLoanPeriodDto);
    }

    @PutMapping("/loan-product/{id}")
    public ResponseEntity<BnRLoanProductDto> updateLoanProduct(@PathVariable Long id, @RequestBody BnRLoanProductDto bnRLoanProductDto) {
        return referenceService.updateLoanProduct(id, bnRLoanProductDto);
    }

    @PutMapping("/loan-type/{id}")
    public ResponseEntity<BnRLoanTypeDto> updateLoanType(@PathVariable Long id, @RequestBody BnRLoanTypeDto bnRLoanTypeDto) {
        return referenceService.updateLoanType(id, bnRLoanTypeDto);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<BnRStatusDto> updateStatus(@PathVariable Long id, @RequestBody BnRStatusDto bnRStatusDto) {
        return referenceService.updateStatus(id, bnRStatusDto);
    }

    @PutMapping("/tran-type/{id}")
    public ResponseEntity<BnRTranTypeDto> updateTranType(@PathVariable Long id, @RequestBody BnRTranTypeDto bnRTranTypeDto) {
        return referenceService.updateTranType(id, bnRTranTypeDto);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<BnRRoleDto> updateRole(@PathVariable Long id, @RequestBody BnRRoleDto bnRRoleDto) {
        return referenceService.updateRole(id, bnRRoleDto);
    }

    @PutMapping("/currency-rate/{id}")
    public ResponseEntity<BnRCurrencyRateDto> updateCurrencyRate(@PathVariable Long id, @RequestBody BnRCurrencyRateDto bnRCurrencyRateDto) {
        return referenceService.updateCurrencyRate(id, bnRCurrencyRateDto);
    }


    /* DELETE APIs */
    @DeleteMapping("/account-type/{id}")
    public ResponseEntity<String> deleteAccountType(@PathVariable Long id) {
        return referenceService.deleteAccountType(id);
    }

    @DeleteMapping("/bank/{id}")
    public ResponseEntity<String> deleteBank(@PathVariable Long id) {
        return referenceService.deleteBank(id);
    }

    @DeleteMapping("/branch/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable Long id) {
        return referenceService.deleteBranch(id);
    }

    @DeleteMapping("/charge/{id}")
    public ResponseEntity<String> deleteCharge(@PathVariable Long id) {
        return referenceService.deleteCharge(id);
    }

    @DeleteMapping("/currency/{id}")
    public ResponseEntity<String> deleteCurrency(@PathVariable Long id) {
        return referenceService.deleteCurrency(id);
    }

    @DeleteMapping("/fee-type/{id}")
    public ResponseEntity<String> deleteFeeType(@PathVariable Long id) {
        return referenceService.deleteFeeType(id);
    }

    @DeleteMapping("/int-rate/{id}")
    public ResponseEntity<String> deleteIntRate(@PathVariable Long id) {
        return referenceService.deleteIntRate(id);
    }

    @DeleteMapping("/loan-period/{id}")
    public ResponseEntity<String> deleteLoanPeriod(@PathVariable Long id) {
        return referenceService.deleteLoanPeriod(id);
    }

    @DeleteMapping("/loan-product/{id}")
    public ResponseEntity<String> deleteLoanProduct(@PathVariable Long id) {
        return referenceService.deleteLoanProduct(id);
    }

    @DeleteMapping("/loan-type/{id}")
    public ResponseEntity<String> deleteLoanType(@PathVariable Long id) {
        return referenceService.deleteLoanType(id);
    }

    @DeleteMapping("/status/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable Long id) {
        return referenceService.deleteStatus(id);
    }

    @DeleteMapping("/tran-type/{id}")
    public ResponseEntity<String> deleteTranType(@PathVariable Long id) {
        return referenceService.deleteTranType(id);
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        return referenceService.deleteRole(id);
    }

    @DeleteMapping("/currency-rate/{id}")
    public ResponseEntity<String> deleteCurrencyRate(@PathVariable Long id) {
        return referenceService.deleteCurrencyRate(id);
    }

}
