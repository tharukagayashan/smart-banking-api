package com.projects.smartbankingapi.service.reference.impl;

import com.projects.smartbankingapi.dao.reference.*;
import com.projects.smartbankingapi.dto.reference.*;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.reference.*;
import com.projects.smartbankingapi.model.reference.*;
import com.projects.smartbankingapi.service.reference.ReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReferenceServiceImpl implements ReferenceService {
    private final BnRAccountTypeRepository accountTypeRepository;
    private final BnRAccountTypeMapper accountTypeMapper;
    private final BnRBankRepository bankRepository;
    private final BnRBankMapper bankMapper;
    private final BnRBranchRepository branchRepository;
    private final BnRBranchMapper branchMapper;
    private final BnRChargeRepository chargeRepository;
    private final BnRChargeMapper chargeMapper;
    private final BnRCurrencyRepository currencyRepository;
    private final BnRCurrencyMapper currencyMapper;
    private final BnRFeeTypeRepository feeTypeRepository;
    private final BnRFeeTypeMapper feeTypeMapper;
    private final BnRIntRateRepository intRateRepository;
    private final BnRIntRateMapper intRateMapper;
    private final BnRLoanPeriodRepository loanPeriodRepository;
    private final BnRLoanPeriodMapper loanPeriodMapper;
    private final BnRLoanProductRepository loanProductRepository;
    private final BnRLoanProductMapper loanProductMapper;
    private final BnRLoanTypeRepository loanTypeRepository;
    private final BnRLoanTypeMapper loanTypeMapper;
    private final BnRStatusRepository statusRepository;
    private final BnRStatusMapper statusMapper;
    private final BnRTranTypeRepository tranTypeRepository;
    private final BnRTranTypeMapper tranTypeMapper;

    public ReferenceServiceImpl(BnRAccountTypeRepository accountTypeRepository, BnRAccountTypeMapper accountTypeMapper, BnRBankRepository bankRepository, BnRBankMapper bankMapper, BnRBranchRepository branchRepository, BnRBranchMapper branchMapper, BnRChargeRepository chargeRepository, BnRChargeMapper chargeMapper, BnRCurrencyRepository currencyRepository, BnRCurrencyMapper currencyMapper, BnRFeeTypeRepository feeTypeRepository, BnRFeeTypeMapper feeTypeMapper, BnRIntRateRepository intRateRepository, BnRIntRateMapper intRateMapper, BnRLoanPeriodRepository loanPeriodRepository, BnRLoanPeriodMapper loanPeriodMapper, BnRLoanProductRepository loanProductRepository, BnRLoanProductMapper loanProductMapper, BnRLoanTypeRepository loanTypeRepository, BnRLoanTypeMapper loanTypeMapper, BnRStatusRepository statusRepository, BnRStatusMapper statusMapper, BnRTranTypeRepository tranTypeRepository, BnRTranTypeMapper tranTypeMapper) {
        this.accountTypeRepository = accountTypeRepository;
        this.accountTypeMapper = accountTypeMapper;
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.chargeRepository = chargeRepository;
        this.chargeMapper = chargeMapper;
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
        this.feeTypeRepository = feeTypeRepository;
        this.feeTypeMapper = feeTypeMapper;
        this.intRateRepository = intRateRepository;
        this.intRateMapper = intRateMapper;
        this.loanPeriodRepository = loanPeriodRepository;
        this.loanPeriodMapper = loanPeriodMapper;
        this.loanProductRepository = loanProductRepository;
        this.loanProductMapper = loanProductMapper;
        this.loanTypeRepository = loanTypeRepository;
        this.loanTypeMapper = loanTypeMapper;
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
        this.tranTypeRepository = tranTypeRepository;
        this.tranTypeMapper = tranTypeMapper;
    }

    @Override
    public ResponseEntity<List<BnRAccountTypeDto>> getAllAccountTypes() {
        try {
            List<BnRAccountType> accountTypes = accountTypeRepository.findAll();
            if (accountTypes.isEmpty()) {
                throw new BadRequestAlertException("No account type found", "Reference", "getAllAccountTypes");
            } else {
                List<BnRAccountTypeDto> accountTypeDtoList = accountTypeMapper.entityListToDtoList(accountTypes);
                return ResponseEntity.ok(accountTypeDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all account types", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllAccountTypes");
        }
    }

    @Override
    public ResponseEntity<List<BnRBankDto>> getAllBanks() {
        try {
            List<BnRBank> banks = bankRepository.findAll();
            if (banks.isEmpty()) {
                throw new BadRequestAlertException("No bank found", "Reference", "getAllBanks");
            } else {
                List<BnRBankDto> bankDtoList = bankMapper.entityListToDtoList(banks);
                return ResponseEntity.ok(bankDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all banks", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllBanks");
        }
    }

    @Override
    public ResponseEntity<List<BnRBranchDto>> getAllBranches() {
        try {
            List<BnRBranch> branches = branchRepository.findAll();
            if (branches.isEmpty()) {
                throw new BadRequestAlertException("No branch found", "Reference", "getAllBranches");
            } else {
                List<BnRBranchDto> branchDtoList = branchMapper.entityListToDtoList(branches);
                return ResponseEntity.ok(branchDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all branches", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllBranches");
        }
    }

    @Override
    public ResponseEntity<List<BnRBranchDto>> getAllBranchesByBankId(Long bankId) {
        try {
            if (bankId == null)
                throw new BadRequestAlertException("Bank id is required", "Reference", "getAllBranchesByBankId");

            List<BnRBranch> branches = branchRepository.findAllByBnRBankBankId(bankId);
            if (branches.isEmpty()) {
                throw new BadRequestAlertException("No branch found", "Reference", "getAllBranchesByBankId");
            } else {
                List<BnRBranchDto> branchDtoList = branchMapper.entityListToDtoList(branches);
                return ResponseEntity.ok(branchDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all branches by bank id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllBranchesByBankId");
        }
    }

    @Override
    public ResponseEntity<List<BnRChargeDto>> getAllCharges() {
        try {
            List<BnRCharge> charges = chargeRepository.findAll();
            if (charges.isEmpty()) {
                throw new BadRequestAlertException("No charge found", "Reference", "getAllCharges");
            } else {
                List<BnRChargeDto> chargeDtoList = chargeMapper.entityListToDtoList(charges);
                return ResponseEntity.ok(chargeDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all charges", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllCharges");
        }
    }

    @Override
    public ResponseEntity<List<BnRCurrencyDto>> getAllCurrencies() {
        try {
            List<BnRCurrency> currencies = currencyRepository.findAll();
            if (currencies.isEmpty()) {
                throw new BadRequestAlertException("No currency found", "Reference", "getAllCurrencies");
            } else {
                List<BnRCurrencyDto> currencyDtoList = currencyMapper.entityListToDtoList(currencies);
                return ResponseEntity.ok(currencyDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all currencies", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllCurrencies");
        }
    }

    @Override
    public ResponseEntity<List<BnRFeeTypeDto>> getAllFeeTypes() {
        try {
            List<BnRFeeType> feeTypes = feeTypeRepository.findAll();
            if (feeTypes.isEmpty()) {
                throw new BadRequestAlertException("No fee type found", "Reference", "getAllFeeTypes");
            } else {
                List<BnRFeeTypeDto> feeTypeDtoList = feeTypeMapper.entityListToDtoList(feeTypes);
                return ResponseEntity.ok(feeTypeDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all fee types", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllFeeTypes");
        }
    }

    @Override
    public ResponseEntity<List<BnRIntRateDto>> getAllIntRates() {
        try {
            List<BnRIntRate> intRates = intRateRepository.findAll();
            if (intRates.isEmpty()) {
                throw new BadRequestAlertException("No interest rate found", "Reference", "getAllIntRates");
            } else {
                List<BnRIntRateDto> intRateDtoList = intRateMapper.entityListToDtoList(intRates);
                return ResponseEntity.ok(intRateDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all interest rates", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllIntRates");
        }
    }

    @Override
    public ResponseEntity<List<BnRLoanPeriodDto>> getAllLoanPeriods() {
        try {
            List<BnRLoanPeriod> loanPeriods = loanPeriodRepository.findAll();
            if (loanPeriods.isEmpty()) {
                throw new BadRequestAlertException("No loan period found", "Reference", "getAllLoanPeriods");
            } else {
                List<BnRLoanPeriodDto> loanPeriodDtoList = loanPeriodMapper.entityListToDtoList(loanPeriods);
                return ResponseEntity.ok(loanPeriodDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all loan periods", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllLoanPeriods");
        }
    }

    @Override
    public ResponseEntity<List<BnRLoanProductDto>> getAllLoanProducts() {
        try {
            List<BnRLoanProduct> loanProducts = loanProductRepository.findAll();
            if (loanProducts.isEmpty()) {
                throw new BadRequestAlertException("No loan product found", "Reference", "getAllLoanProducts");
            } else {
                List<BnRLoanProductDto> loanProductDtoList = loanProductMapper.entityListToDtoList(loanProducts);
                return ResponseEntity.ok(loanProductDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all loan products", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllLoanProducts");
        }
    }

    @Override
    public ResponseEntity<List<BnRLoanTypeDto>> getAllLoanTypes() {
        try {
            List<BnRLoanType> loanTypes = loanTypeRepository.findAll();
            if (loanTypes.isEmpty()) {
                throw new BadRequestAlertException("No loan type found", "Reference", "getAllLoanTypes");
            } else {
                List<BnRLoanTypeDto> loanTypeDtoList = loanTypeMapper.entityListToDtoList(loanTypes);
                return ResponseEntity.ok(loanTypeDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all loan types", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllLoanTypes");
        }
    }

    @Override
    public ResponseEntity<List<BnRStatusDto>> getAllStatuses() {
        try {
            List<BnRStatus> statuses = statusRepository.findAll();
            if (statuses.isEmpty()) {
                throw new BadRequestAlertException("No status found", "Reference", "getAllStatuses");
            } else {
                List<BnRStatusDto> statusDtoList = statusMapper.entityListToDtoList(statuses);
                return ResponseEntity.ok(statusDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all statuses", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllStatuses");
        }
    }

    @Override
    public ResponseEntity<List<BnRTranTypeDto>> getAllTranTypes() {
        try {
            List<BnRTranType> tranTypes = tranTypeRepository.findAll();
            if (tranTypes.isEmpty()) {
                throw new BadRequestAlertException("No transaction type found", "Reference", "getAllTranTypes");
            } else {
                List<BnRTranTypeDto> tranTypeDtoList = tranTypeMapper.entityListToDtoList(tranTypes);
                return ResponseEntity.ok(tranTypeDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all transaction types", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllTranTypes");
        }
    }
}
