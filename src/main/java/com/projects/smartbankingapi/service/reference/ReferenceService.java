package com.projects.smartbankingapi.service.reference;

import com.projects.smartbankingapi.dto.reference.*;
import com.projects.smartbankingapi.model.reference.BnRAccountType;
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
}
