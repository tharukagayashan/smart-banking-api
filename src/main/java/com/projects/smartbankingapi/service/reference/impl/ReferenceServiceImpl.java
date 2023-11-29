package com.projects.smartbankingapi.service.reference.impl;

import com.projects.smartbankingapi.dao.reference.*;
import com.projects.smartbankingapi.dto.other.*;
import com.projects.smartbankingapi.dto.reference.*;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.reference.*;
import com.projects.smartbankingapi.model.reference.*;
import com.projects.smartbankingapi.service.reference.ReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public ResponseEntity<List<BnRLoanProductDto>> getAllLoanProductsByFilter(Long loanTypeId, Long intRateId, Long periodId) {
        try {
            List<BnRLoanProduct> productList;
            if (loanTypeId == null && intRateId == null && periodId == null)
                throw new BadRequestAlertException("At least one filter is required", "Reference", "getAllLoanProductsByFilter");
            else if (loanTypeId != null && intRateId == null && periodId == null)
                productList = loanProductRepository.findAllByBnRLoanTypeLoanTypeId(loanTypeId);
            else if (loanTypeId == null && intRateId != null && periodId == null)
                productList = loanProductRepository.findAllByBnRIntRateIntRateId(intRateId);
            else if (loanTypeId == null && intRateId == null && periodId != null)
                productList = loanProductRepository.findAllByBnRLoanPeriodPeriodId(periodId);
            else if (loanTypeId != null && intRateId != null && periodId == null)
                productList = loanProductRepository.findAllByBnRLoanTypeLoanTypeIdAndBnRIntRateIntRateId(loanTypeId, intRateId);
            else if (loanTypeId != null && intRateId == null && periodId != null)
                productList = loanProductRepository.findAllByBnRLoanTypeLoanTypeIdAndBnRLoanPeriodPeriodId(loanTypeId, periodId);
            else if (loanTypeId == null && intRateId != null && periodId != null)
                productList = loanProductRepository.findAllByBnRIntRateIntRateIdAndBnRLoanPeriodPeriodId(intRateId, periodId);
            else
                productList = loanProductRepository.findAllByBnRLoanTypeLoanTypeIdAndBnRIntRateIntRateIdAndBnRLoanPeriodPeriodId(loanTypeId, intRateId, periodId);

            if (productList.isEmpty())
                throw new BadRequestAlertException("No loan product found", "Reference", "getAllLoanProductsByFilter");
            else {
                List<BnRLoanProductDto> productDtoList = loanProductMapper.entityListToDtoList(productList);
                return ResponseEntity.ok(productDtoList);
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllLoanProductsByFilter");
        }
    }

    @Override
    public ResponseEntity<BnRAccountTypeDto> createAccountType(AccountTypeCreateReqDto accountTypeCreateReqDto) {
        try {
            Optional<BnRAccountType> optAccountType = accountTypeRepository.findByCode(accountTypeCreateReqDto.getCode());
            if (optAccountType.isPresent()) {
                throw new BadRequestAlertException("Account type already exists for given code", "Reference", "createAccountType");
            } else {
                BnRAccountType accountType = new BnRAccountType();
                accountType.setName(accountTypeCreateReqDto.getName());
                accountType.setCode(accountTypeCreateReqDto.getCode());
                accountType = accountTypeRepository.save(accountType);
                if (accountType.getAccountTypeId() == null) {
                    throw new BadRequestAlertException("Error occurred while creating account type", "Reference", "createAccountType");
                } else {
                    BnRAccountTypeDto accountTypeDto = accountTypeMapper.toDto(accountType);
                    return ResponseEntity.ok(accountTypeDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while creating account type", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createAccountType");
        }
    }

    @Override
    public ResponseEntity<BnRBankDto> createBank(BankCreateReqDto bankCreateReqDto) {
        try {
            Optional<BnRBank> optBank = bankRepository.findByCode(bankCreateReqDto.getCode());
            if (optBank.isPresent()) {
                throw new BadRequestAlertException("Bank already exists for given code", "Reference", "createBank");
            } else {
                BnRBank bank = new BnRBank();
                bank.setName(bankCreateReqDto.getName());
                bank.setCode(bankCreateReqDto.getCode());
                bank.setIsActive(true);
                bank = bankRepository.save(bank);

                if (bank.getBankId() == null) {
                    throw new BadRequestAlertException("Error occurred while creating bank", "Reference", "createBank");
                } else {
                    BnRBankDto bankDto = bankMapper.toDto(bank);
                    return ResponseEntity.ok(bankDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while creating bank", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createBank");
        }
    }

    @Override
    public ResponseEntity<BnRBranchDto> createBranch(BranchCreateReqDto branchCreateReqDto) {
        try {
            Optional<BnRBank> optBank = bankRepository.findById(branchCreateReqDto.getBankId());
            if (!optBank.isPresent()) {
                throw new BadRequestAlertException("Bank not found for given id", "Reference", "createBranch");
            } else {
                Optional<BnRBranch> optBranch = branchRepository.findByCode(branchCreateReqDto.getCode());
                if (optBranch.isPresent()) {
                    throw new BadRequestAlertException("Branch already exists for given code", "Reference", "createBranch");
                } else {
                    BnRBranch branch = new BnRBranch();
                    branch.setName(branchCreateReqDto.getName());
                    branch.setCode(branchCreateReqDto.getCode());
                    branch.setBnRBank(optBank.get());
                    branch.setIsActive(true);
                    branch = branchRepository.save(branch);
                    if (branch.getBranchId() == null) {
                        throw new BadRequestAlertException("Error occurred while creating branch", "Reference", "createBranch");
                    } else {
                        BnRBranchDto branchDto = branchMapper.toDto(branch);
                        return ResponseEntity.ok(branchDto);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while creating branch", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createBranch");
        }
    }

    @Override
    public ResponseEntity<BnRChargeDto> createCharge(ChargeCreateReqDto chargeCreateReqDto) {
        try {
            Optional<BnRFeeType> optFeeType = feeTypeRepository.findById(chargeCreateReqDto.getFeeTypeId());
            Optional<BnRCurrency> optCurrency = currencyRepository.findById(chargeCreateReqDto.getCurrencyId());
            if (!optFeeType.isPresent()) {
                throw new BadRequestAlertException("Fee type not found for given id", "Reference", "createCharge");
            }
            if (!optCurrency.isPresent()) {
                throw new BadRequestAlertException("Currency not found for given id", "Reference", "createCharge");
            }
            BnRCharge charge = new BnRCharge();
            charge.setDescription(chargeCreateReqDto.getDescription());
            charge.setAmount(chargeCreateReqDto.getAmount());
            charge.setEffectiveDate(chargeCreateReqDto.getEffectiveDate());
            charge.setExpirationDate(chargeCreateReqDto.getExpirationDate());
            charge.setBnRFeeType(optFeeType.get());
            charge.setBnRCurrency(optCurrency.get());
            charge = chargeRepository.save(charge);
            if (charge.getChargeId() == null) {
                throw new BadRequestAlertException("Error occurred while creating charge", "Reference", "createCharge");
            } else {
                BnRChargeDto chargeDto = chargeMapper.toDto(charge);
                return ResponseEntity.ok(chargeDto);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating charge", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createCharge");
        }
    }

    @Override
    public ResponseEntity<BnRCurrencyDto> createCurrency(CurrencyCreateReqDto currencyCreateReqDto) {
        try {
            Optional<BnRCurrency> optCurrency = currencyRepository.findByCode(currencyCreateReqDto.getCode());
            if (optCurrency.isPresent()) {
                throw new BadRequestAlertException("Currency already exists for given code", "Reference", "createCurrency");
            } else {
                BnRCurrency currency = new BnRCurrency();
                currency.setName(currencyCreateReqDto.getName());
                currency.setCode(currencyCreateReqDto.getCode());
                currency = currencyRepository.save(currency);
                if (currency.getCurrencyId() == null) {
                    throw new BadRequestAlertException("Error occurred while creating currency", "Reference", "createCurrency");
                } else {
                    BnRCurrencyDto currencyDto = currencyMapper.toDto(currency);
                    return ResponseEntity.ok(currencyDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while creating currency", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createCurrency");
        }
    }

    @Override
    public ResponseEntity<BnRFeeTypeDto> createFeeType(FeeTypeCreateReqDto feeTypeCreateReqDto) {
        try {
            BnRFeeType feeType = new BnRFeeType();
            feeType.setName(feeTypeCreateReqDto.getName());
            feeType.setDescription(feeTypeCreateReqDto.getDescription());
            feeType = feeTypeRepository.save(feeType);
            if (feeType.getFeeTypeId() == null) {
                throw new BadRequestAlertException("Error occurred while creating fee type", "Reference", "createFeeType");
            } else {
                BnRFeeTypeDto feeTypeDto = feeTypeMapper.toDto(feeType);
                return ResponseEntity.ok(feeTypeDto);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating fee type", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createFeeType");
        }
    }

    @Override
    public ResponseEntity<BnRIntRateDto> createIntRate(IntRateCreateReqDto intRateCreateReqDto) {
        try {
            BnRIntRate intRate = new BnRIntRate();
            intRate.setName(intRateCreateReqDto.getName());
            intRate.setDescription(intRateCreateReqDto.getDescription());
            intRate.setRate(intRateCreateReqDto.getRate());
            intRate = intRateRepository.save(intRate);
            if (intRate.getIntRateId() == null) {
                throw new BadRequestAlertException("Error occurred while creating interest rate", "Reference", "createIntRate");
            } else {
                BnRIntRateDto intRateDto = intRateMapper.toDto(intRate);
                return ResponseEntity.ok(intRateDto);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating interest rate", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createIntRate");
        }
    }

    @Override
    public ResponseEntity<BnRLoanPeriodDto> createLoanPeriod(LoanPeriodCreateReqDto loanPeriodCreateReqDto) {
        try {
            BnRLoanPeriod loanPeriod = new BnRLoanPeriod();
            loanPeriod.setName(loanPeriodCreateReqDto.getName());
            loanPeriod.setDescription(loanPeriodCreateReqDto.getDescription());
            loanPeriod.setMonth(loanPeriodCreateReqDto.getMonth());
            loanPeriod = loanPeriodRepository.save(loanPeriod);
            if (loanPeriod.getPeriodId() == null) {
                throw new BadRequestAlertException("Error occurred while creating loan period", "Reference", "createLoanPeriod");
            } else {
                BnRLoanPeriodDto loanPeriodDto = loanPeriodMapper.toDto(loanPeriod);
                return ResponseEntity.ok(loanPeriodDto);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating loan period", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createLoanPeriod");
        }
    }

    @Override
    public ResponseEntity<BnRLoanProductDto> createLoanProduct(LoanProductCreateReqDto loanProductCreateReqDto) {
        try {
            BnRLoanProduct loanProduct = new BnRLoanProduct();
            Optional<BnRLoanType> optLoanType = loanTypeRepository.findById(loanProductCreateReqDto.getLoanTypeId());
            Optional<BnRIntRate> optIntRate = intRateRepository.findById(loanProductCreateReqDto.getIntRateId());
            Optional<BnRLoanPeriod> optLoanPeriod = loanPeriodRepository.findById(loanProductCreateReqDto.getPeriodId());
            if (!optLoanType.isPresent()) {
                throw new BadRequestAlertException("Loan type not found for given id", "Reference", "createLoanProduct");
            } else if (!optIntRate.isPresent()) {
                throw new BadRequestAlertException("Interest rate not found for given id", "Reference", "createLoanProduct");
            } else if (!optLoanPeriod.isPresent()) {
                throw new BadRequestAlertException("Loan period not found for given id", "Reference", "createLoanProduct");
            } else {
                log.info("Loan type, interest rate and loan period found");
            }
            loanProduct.setBnRLoanType(optLoanType.get());
            loanProduct.setBnRIntRate(optIntRate.get());
            loanProduct.setBnRLoanPeriod(optLoanPeriod.get());
            loanProduct = loanProductRepository.save(loanProduct);
            if (loanProduct.getProductId() == null) {
                throw new BadRequestAlertException("Error occurred while creating loan product", "Reference", "createLoanProduct");
            } else {
                BnRLoanProductDto loanProductDto = loanProductMapper.toDto(loanProduct);
                return ResponseEntity.ok(loanProductDto);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating loan product", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createLoanProduct");
        }
    }

    @Override
    public ResponseEntity<BnRLoanTypeDto> createLoanType(LoanTypeCreateReqDto loanTypeCreateReqDto) {
        try {
            Optional<BnRLoanType> optLoanType = loanTypeRepository.findByCode(loanTypeCreateReqDto.getCode());
            if (optLoanType.isPresent()) {
                throw new BadRequestAlertException("Loan type already exists for given code", "Reference", "createLoanType");
            } else {
                BnRLoanType loanType = new BnRLoanType();
                loanType.setName(loanTypeCreateReqDto.getName());
                loanType.setCode(loanTypeCreateReqDto.getCode());
                loanType.setDescription(loanTypeCreateReqDto.getDescription());
                loanType = loanTypeRepository.save(loanType);
                if (loanType.getLoanTypeId() == null) {
                    throw new BadRequestAlertException("Error occurred while creating loan type", "Reference", "createLoanType");
                } else {
                    BnRLoanTypeDto loanTypeDto = loanTypeMapper.toDto(loanType);
                    return ResponseEntity.ok(loanTypeDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while creating loan type", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createLoanType");
        }
    }

    @Override
    public ResponseEntity<BnRStatusDto> createStatus(StatusCreateReqDto statusCreateReqDto) {
        try {
            Optional<BnRStatus> optStatus = statusRepository.findByCode(statusCreateReqDto.getCode());
            if (optStatus.isPresent()) {
                throw new BadRequestAlertException("Status already exists for given code", "Reference", "createStatus");
            } else {
                BnRStatus status = new BnRStatus();
                status.setName(statusCreateReqDto.getName());
                status.setCode(statusCreateReqDto.getCode());
                status.setType(statusCreateReqDto.getType());
                status = statusRepository.save(status);
                if (status.getStatusId() == null) {
                    throw new BadRequestAlertException("Error occurred while creating status", "Reference", "createStatus");
                } else {
                    BnRStatusDto statusDto = statusMapper.toDto(status);
                    return ResponseEntity.ok(statusDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while creating status", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createStatus");
        }
    }

    @Override
    public ResponseEntity<BnRTranTypeDto> createTranType(TranTypeCreateReqDto tranTypeCreateReqDto) {
        try {
            Optional<BnRTranType> optTranType = tranTypeRepository.findByCode(tranTypeCreateReqDto.getCode());
            if (optTranType.isPresent()) {
                throw new BadRequestAlertException("Transaction type already exists for given code", "Reference", "createTranType");
            } else {
                BnRTranType tranType = new BnRTranType();
                tranType.setName(tranTypeCreateReqDto.getName());
                tranType.setCode(tranTypeCreateReqDto.getCode());
                tranType = tranTypeRepository.save(tranType);
                if (tranType.getTranTypeId() == null) {
                    throw new BadRequestAlertException("Error occurred while creating transaction type", "Reference", "createTranType");
                } else {
                    BnRTranTypeDto tranTypeDto = tranTypeMapper.toDto(tranType);
                    return ResponseEntity.ok(tranTypeDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while creating transaction type", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createTranType");
        }
    }
}