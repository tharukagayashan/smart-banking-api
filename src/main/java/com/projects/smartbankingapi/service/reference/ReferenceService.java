package com.projects.smartbankingapi.service.reference;

import com.projects.smartbankingapi.dto.other.*;
import com.projects.smartbankingapi.dto.reference.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReferenceService {
    ResponseEntity<List<BnRAccountTypeDto>> getAllAccountTypes();

    ResponseEntity<List<BnRBankDto>> getAllBanks();

    ResponseEntity<List<BnRBranchDto>> getAllBranches();

    ResponseEntity<List<BnRBranchDto>> getAllBranchesByBankId(Long bankId);

    ResponseEntity<List<BnRChargeDto>> getAllCharges();

    ResponseEntity<List<BnRCurrencyDto>> getAllCurrencies();

    ResponseEntity<List<BnRFeeTypeDto>> getAllFeeTypes();

    ResponseEntity<List<BnRIntRateDto>> getAllIntRates();

    ResponseEntity<List<BnRLoanPeriodDto>> getAllLoanPeriods();

    ResponseEntity<List<BnRLoanProductDto>> getAllLoanProducts();

    ResponseEntity<List<BnRLoanTypeDto>> getAllLoanTypes();

    ResponseEntity<List<BnRStatusDto>> getAllStatuses();

    ResponseEntity<List<BnRTranTypeDto>> getAllTranTypes();

    ResponseEntity<List<BnRLoanProductDto>> getAllLoanProductsByFilter(Long loanTypeId, Long intRateId, Long periodId);

    ResponseEntity<List<BnRRoleDto>> getAllRoles();

    ResponseEntity<BnRAccountTypeDto> createAccountType(AccountTypeCreateReqDto accountTypeCreateReqDto);

    ResponseEntity<BnRBankDto> createBank(BankCreateReqDto bankCreateReqDto);

    ResponseEntity<BnRBranchDto> createBranch(BranchCreateReqDto branchCreateReqDto);

    ResponseEntity<BnRChargeDto> createCharge(ChargeCreateReqDto chargeCreateReqDto);

    ResponseEntity<BnRCurrencyDto> createCurrency(CurrencyCreateReqDto currencyCreateReqDto);

    ResponseEntity<BnRFeeTypeDto> createFeeType(FeeTypeCreateReqDto feeTypeCreateReqDto);

    ResponseEntity<BnRIntRateDto> createIntRate(IntRateCreateReqDto intRateCreateReqDto);

    ResponseEntity<BnRLoanPeriodDto> createLoanPeriod(LoanPeriodCreateReqDto loanPeriodCreateReqDto);

    ResponseEntity<BnRLoanProductDto> createLoanProduct(LoanProductCreateReqDto loanProductCreateReqDto);

    ResponseEntity<BnRLoanTypeDto> createLoanType(LoanTypeCreateReqDto loanTypeCreateReqDto);

    ResponseEntity<BnRStatusDto> createStatus(StatusCreateReqDto statusCreateReqDto);

    ResponseEntity<BnRTranTypeDto> createTranType(TranTypeCreateReqDto tranTypeCreateReqDto);

    ResponseEntity<BnRRoleDto> createRole(RoleCreateReqDto roleCreateReqDto);

    ResponseEntity<BnRAccountTypeDto> getAccountTypeById(Long id);

    ResponseEntity<BnRBankDto> getBankById(Long id);

    ResponseEntity<BnRBranchDto> getBranchById(Long id);

    ResponseEntity<BnRChargeDto> getChargeById(Long id);

    ResponseEntity<BnRCurrencyDto> getCurrencyById(Long id);

    ResponseEntity<BnRFeeTypeDto> getFeeTypeById(Long id);

    ResponseEntity<BnRIntRateDto> getIntRateById(Long id);

    ResponseEntity<BnRLoanPeriodDto> getLoanPeriodById(Long id);

    ResponseEntity<BnRLoanProductDto> getLoanProductById(Long id);

    ResponseEntity<BnRLoanTypeDto> getLoanTypeById(Long id);

    ResponseEntity<BnRStatusDto> getStatusById(Long id);

    ResponseEntity<BnRTranTypeDto> getTranTypeById(Long id);

    ResponseEntity<BnRRoleDto> getRoleById(Long id);

    ResponseEntity<BnRAccountTypeDto> updateAccountType(Long id, BnRAccountTypeDto bnRAccountTypeDto);

    ResponseEntity<BnRBankDto> updateBank(Long id, BnRBankDto bnRBankDto);

    ResponseEntity<BnRBranchDto> updateBranch(Long id, BnRBranchDto bnRBranchDto);

    ResponseEntity<BnRChargeDto> updateCharge(Long id, BnRChargeDto bnRChargeDto);

    ResponseEntity<BnRCurrencyDto> updateCurrency(Long id, BnRCurrencyDto bnRCurrencyDto);

    ResponseEntity<BnRFeeTypeDto> updateFeeType(Long id, BnRFeeTypeDto bnRFeeTypeDto);

    ResponseEntity<BnRIntRateDto> updateIntRate(Long id, BnRIntRateDto bnRIntRateDto);

    ResponseEntity<BnRLoanPeriodDto> updateLoanPeriod(Long id, BnRLoanPeriodDto bnRLoanPeriodDto);

    ResponseEntity<BnRLoanProductDto> updateLoanProduct(Long id, BnRLoanProductDto bnRLoanProductDto);

    ResponseEntity<BnRLoanTypeDto> updateLoanType(Long id, BnRLoanTypeDto bnRLoanTypeDto);

    ResponseEntity<BnRStatusDto> updateStatus(Long id, BnRStatusDto bnRStatusDto);

    ResponseEntity<BnRTranTypeDto> updateTranType(Long id, BnRTranTypeDto bnRTranTypeDto);

    ResponseEntity<BnRRoleDto> updateRole(Long id, BnRRoleDto bnRRoleDto);

    ResponseEntity<String> deleteAccountType(Long id);

    ResponseEntity<String> deleteBank(Long id);

    ResponseEntity<String> deleteBranch(Long id);

    ResponseEntity<String> deleteCharge(Long id);

    ResponseEntity<String> deleteCurrency(Long id);

    ResponseEntity<String> deleteFeeType(Long id);

    ResponseEntity<String> deleteIntRate(Long id);

    ResponseEntity<String> deleteLoanPeriod(Long id);

    ResponseEntity<String> deleteLoanProduct(Long id);

    ResponseEntity<String> deleteLoanType(Long id);

    ResponseEntity<String> deleteStatus(Long id);

    ResponseEntity<String> deleteTranType(Long id);

    ResponseEntity<String> deleteRole(Long id);
}
