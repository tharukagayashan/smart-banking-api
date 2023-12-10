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
    private final BnRRoleRepository roleRepository;
    private final BnRRoleMapper roleMapper;

    public ReferenceServiceImpl(BnRAccountTypeRepository accountTypeRepository, BnRAccountTypeMapper accountTypeMapper, BnRBankRepository bankRepository, BnRBankMapper bankMapper, BnRBranchRepository branchRepository, BnRBranchMapper branchMapper, BnRChargeRepository chargeRepository, BnRChargeMapper chargeMapper, BnRCurrencyRepository currencyRepository, BnRCurrencyMapper currencyMapper, BnRFeeTypeRepository feeTypeRepository, BnRFeeTypeMapper feeTypeMapper, BnRIntRateRepository intRateRepository, BnRIntRateMapper intRateMapper, BnRLoanPeriodRepository loanPeriodRepository, BnRLoanPeriodMapper loanPeriodMapper, BnRLoanProductRepository loanProductRepository, BnRLoanProductMapper loanProductMapper, BnRLoanTypeRepository loanTypeRepository, BnRLoanTypeMapper loanTypeMapper, BnRStatusRepository statusRepository, BnRStatusMapper statusMapper, BnRTranTypeRepository tranTypeRepository, BnRTranTypeMapper tranTypeMapper, BnRRoleRepository roleRepository, BnRRoleMapper roleMapper) {
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
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
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
    public ResponseEntity<List<BnRRoleDto>> getAllRoles() {
        try {

            List<BnRRole> roles = roleRepository.findAll();
            if (roles.isEmpty()) {
                throw new BadRequestAlertException("No role found", "Reference", "getAllRoles");
            } else {
                List<BnRRoleDto> roleDtoList = roleMapper.entityListToDtoList(roles);
                return ResponseEntity.ok(roleDtoList);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching all roles", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAllRoles");
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

    @Override
    public ResponseEntity<BnRRoleDto> createRole(RoleCreateReqDto roleCreateReqDto) {
        try {
            Optional<BnRRole> optRole = roleRepository.findByRoleName(roleCreateReqDto.getRoleName());
            if (optRole.isPresent()) {
                throw new BadRequestAlertException("Role already exists for given role name", "Reference", "createRole");
            } else {
                BnRRole role = new BnRRole();
                role.setRoleName(roleCreateReqDto.getRoleName());
                role.setDescription(roleCreateReqDto.getDescription());
                role = roleRepository.save(role);
                if (role.getRoleId() == null) {
                    throw new BadRequestAlertException("Error occurred while creating role", "Reference", "createRole");
                } else {
                    BnRRoleDto roleDto = roleMapper.toDto(role);
                    return ResponseEntity.ok(roleDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while creating role", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "createRole");
        }
    }

    @Override
    public ResponseEntity<BnRAccountTypeDto> getAccountTypeById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Account type id is required", "Reference", "getAccountTypeById");
            } else {
                Optional<BnRAccountType> optAccountType = accountTypeRepository.findById(id);
                if (!optAccountType.isPresent()) {
                    throw new BadRequestAlertException("Account type not found for given id", "Reference", "getAccountTypeById");
                } else {
                    BnRAccountTypeDto accountTypeDto = accountTypeMapper.toDto(optAccountType.get());
                    return ResponseEntity.ok(accountTypeDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching account type by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getAccountTypeById");
        }
    }

    @Override
    public ResponseEntity<BnRBankDto> getBankById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Bank id is required", "Reference", "getBankById");
            } else {
                Optional<BnRBank> optBank = bankRepository.findById(id);
                if (!optBank.isPresent()) {
                    throw new BadRequestAlertException("Bank not found for given id", "Reference", "getBankById");
                } else {
                    BnRBankDto bankDto = bankMapper.toDto(optBank.get());
                    return ResponseEntity.ok(bankDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching bank by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getBankById");
        }
    }

    @Override
    public ResponseEntity<BnRBranchDto> getBranchById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Branch id is required", "Reference", "getBranchById");
            } else {
                Optional<BnRBranch> optBranch = branchRepository.findById(id);
                if (!optBranch.isPresent()) {
                    throw new BadRequestAlertException("Branch not found for given id", "Reference", "getBranchById");
                } else {
                    BnRBranchDto branchDto = branchMapper.toDto(optBranch.get());
                    return ResponseEntity.ok(branchDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching branch by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getBranchById");
        }
    }

    @Override
    public ResponseEntity<BnRChargeDto> getChargeById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Charge id is required", "Reference", "getChargeById");
            } else {
                Optional<BnRCharge> optCharge = chargeRepository.findById(id);
                if (!optCharge.isPresent()) {
                    throw new BadRequestAlertException("Charge not found for given id", "Reference", "getChargeById");
                } else {
                    BnRChargeDto chargeDto = chargeMapper.toDto(optCharge.get());
                    return ResponseEntity.ok(chargeDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching charge by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getChargeById");
        }
    }

    @Override
    public ResponseEntity<BnRCurrencyDto> getCurrencyById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Currency id is required", "Reference", "getCurrencyById");
            } else {
                Optional<BnRCurrency> optCurrency = currencyRepository.findById(id);
                if (!optCurrency.isPresent()) {
                    throw new BadRequestAlertException("Currency not found for given id", "Reference", "getCurrencyById");
                } else {
                    BnRCurrencyDto currencyDto = currencyMapper.toDto(optCurrency.get());
                    return ResponseEntity.ok(currencyDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching currency by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getCurrencyById");
        }
    }

    @Override
    public ResponseEntity<BnRFeeTypeDto> getFeeTypeById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Fee type id is required", "Reference", "getFeeTypeById");
            } else {
                Optional<BnRFeeType> optFeeType = feeTypeRepository.findById(id);
                if (!optFeeType.isPresent()) {
                    throw new BadRequestAlertException("Fee type not found for given id", "Reference", "getFeeTypeById");
                } else {
                    BnRFeeTypeDto feeTypeDto = feeTypeMapper.toDto(optFeeType.get());
                    return ResponseEntity.ok(feeTypeDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching fee type by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getFeeTypeById");
        }
    }

    @Override
    public ResponseEntity<BnRIntRateDto> getIntRateById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Interest rate id is required", "Reference", "getIntRateById");
            } else {
                Optional<BnRIntRate> optIntRate = intRateRepository.findById(id);
                if (!optIntRate.isPresent()) {
                    throw new BadRequestAlertException("Interest rate not found for given id", "Reference", "getIntRateById");
                } else {
                    BnRIntRateDto intRateDto = intRateMapper.toDto(optIntRate.get());
                    return ResponseEntity.ok(intRateDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching interest rate by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getIntRateById");
        }
    }

    @Override
    public ResponseEntity<BnRLoanPeriodDto> getLoanPeriodById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Loan period id is required", "Reference", "getLoanPeriodById");
            } else {
                Optional<BnRLoanPeriod> optLoanPeriod = loanPeriodRepository.findById(id);
                if (!optLoanPeriod.isPresent()) {
                    throw new BadRequestAlertException("Loan period not found for given id", "Reference", "getLoanPeriodById");
                } else {
                    BnRLoanPeriodDto loanPeriodDto = loanPeriodMapper.toDto(optLoanPeriod.get());
                    return ResponseEntity.ok(loanPeriodDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching loan period by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getLoanPeriodById");
        }
    }

    @Override
    public ResponseEntity<BnRLoanProductDto> getLoanProductById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Loan product id is required", "Reference", "getLoanProductById");
            } else {
                Optional<BnRLoanProduct> optLoanProduct = loanProductRepository.findById(id);
                if (!optLoanProduct.isPresent()) {
                    throw new BadRequestAlertException("Loan product not found for given id", "Reference", "getLoanProductById");
                } else {
                    BnRLoanProductDto loanProductDto = loanProductMapper.toDto(optLoanProduct.get());
                    return ResponseEntity.ok(loanProductDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching loan product by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getLoanProductById");
        }
    }

    @Override
    public ResponseEntity<BnRLoanTypeDto> getLoanTypeById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Loan type id is required", "Reference", "getLoanTypeById");
            } else {
                Optional<BnRLoanType> optLoanType = loanTypeRepository.findById(id);
                if (!optLoanType.isPresent()) {
                    throw new BadRequestAlertException("Loan type not found for given id", "Reference", "getLoanTypeById");
                } else {
                    BnRLoanTypeDto loanTypeDto = loanTypeMapper.toDto(optLoanType.get());
                    return ResponseEntity.ok(loanTypeDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching loan type by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getLoanTypeById");
        }
    }

    @Override
    public ResponseEntity<BnRStatusDto> getStatusById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Status id is required", "Reference", "getStatusById");
            } else {
                Optional<BnRStatus> optStatus = statusRepository.findById(id);
                if (!optStatus.isPresent()) {
                    throw new BadRequestAlertException("Status not found for given id", "Reference", "getStatusById");
                } else {
                    BnRStatusDto statusDto = statusMapper.toDto(optStatus.get());
                    return ResponseEntity.ok(statusDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching status by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getStatusById");
        }
    }

    @Override
    public ResponseEntity<BnRTranTypeDto> getTranTypeById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Transaction type id is required", "Reference", "getTranTypeById");
            } else {
                Optional<BnRTranType> optTranType = tranTypeRepository.findById(id);
                if (!optTranType.isPresent()) {
                    throw new BadRequestAlertException("Transaction type not found for given id", "Reference", "getTranTypeById");
                } else {
                    BnRTranTypeDto tranTypeDto = tranTypeMapper.toDto(optTranType.get());
                    return ResponseEntity.ok(tranTypeDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching transaction type by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getTranTypeById");
        }
    }

    @Override
    public ResponseEntity<BnRRoleDto> getRoleById(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Role id is required", "Reference", "getRoleById");
            } else {
                Optional<BnRRole> optRole = roleRepository.findById(id);
                if (!optRole.isPresent()) {
                    throw new BadRequestAlertException("Role not found for given id", "Reference", "getRoleById");
                } else {
                    BnRRoleDto roleDto = roleMapper.toDto(optRole.get());
                    return ResponseEntity.ok(roleDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching role by id", e);
            throw new BadRequestAlertException(e.getMessage(), "Reference", "getRoleById");
        }
    }

    @Override
    public ResponseEntity<BnRAccountTypeDto> updateAccountType(Long id, BnRAccountTypeDto bnRAccountTypeDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Account type id is required", "Reference", "updateAccountType");
            } else if (!id.equals(bnRAccountTypeDto.getAccountTypeId())) {
                throw new BadRequestAlertException("Account type id mismatch", "Reference", "updateAccountType");
            } else {
                Optional<BnRAccountType> optAccountType = accountTypeRepository.findById(id);
                if (!optAccountType.isPresent()) {
                    throw new BadRequestAlertException("Account type not found for given id", "Reference", "updateAccountType");
                } else {
                    BnRAccountType accountType = optAccountType.get();
                    accountType.setName(bnRAccountTypeDto.getName());
                    accountType.setCode(bnRAccountTypeDto.getCode());
                    accountType = accountTypeRepository.save(accountType);
                    return ResponseEntity.ok(accountTypeMapper.toDto(accountType));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateAccountType");
        }
    }

    @Override
    public ResponseEntity<BnRBankDto> updateBank(Long id, BnRBankDto bnRBankDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Bank id is required", "Reference", "updateBank");
            } else if (!id.equals(bnRBankDto.getBankId())) {
                throw new BadRequestAlertException("Bank id mismatch", "Reference", "updateBank");
            } else {
                Optional<BnRBank> optBank = bankRepository.findById(id);
                if (!optBank.isPresent()) {
                    throw new BadRequestAlertException("Bank not found for given id", "Reference", "updateBank");
                } else {
                    BnRBank bank = optBank.get();
                    bank.setName(bnRBankDto.getName());
                    bank.setCode(bnRBankDto.getCode());
                    bank.setIsActive(bnRBankDto.getIsActive());
                    bank = bankRepository.save(bank);
                    return ResponseEntity.ok(bankMapper.toDto(bank));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateBank");
        }
    }

    @Override
    public ResponseEntity<BnRBranchDto> updateBranch(Long id, BnRBranchDto bnRBranchDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Branch id is required", "Reference", "updateBranch");
            } else if (!id.equals(bnRBranchDto.getBranchId())) {
                throw new BadRequestAlertException("Branch id mismatch", "Reference", "updateBranch");
            } else {
                Optional<BnRBranch> optBranch = branchRepository.findById(id);
                if (!optBranch.isPresent()) {
                    throw new BadRequestAlertException("Branch not found for given id", "Reference", "updateBranch");
                } else {
                    Optional<BnRBank> optBank = bankRepository.findById(bnRBranchDto.getBankId());
                    if (!optBank.isPresent()) {
                        throw new BadRequestAlertException("Bank not found for given id", "Reference", "updateBranch");
                    } else {
                        BnRBranch branch = optBranch.get();
                        branch.setName(bnRBranchDto.getName());
                        branch.setCode(bnRBranchDto.getCode());
                        branch.setBnRBank(optBank.get());
                        branch.setIsActive(bnRBranchDto.getIsActive());
                        branch = branchRepository.save(branch);
                        return ResponseEntity.ok(branchMapper.toDto(branch));
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateBranch");
        }
    }

    @Override
    public ResponseEntity<BnRChargeDto> updateCharge(Long id, BnRChargeDto bnRChargeDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Charge id is required", "Reference", "updateCharge");
            } else if (!id.equals(bnRChargeDto.getChargeId())) {
                throw new BadRequestAlertException("Charge id mismatch", "Reference", "updateCharge");
            } else {
                Optional<BnRCharge> optCharge = chargeRepository.findById(id);
                if (!optCharge.isPresent()) {
                    throw new BadRequestAlertException("Charge not found for given id", "Reference", "updateCharge");
                } else {
                    Optional<BnRFeeType> optFeeType = feeTypeRepository.findById(bnRChargeDto.getFeeTypeId());
                    Optional<BnRCurrency> optCurrency = currencyRepository.findById(bnRChargeDto.getCurrencyId());
                    if (!optFeeType.isPresent()) {
                        throw new BadRequestAlertException("Fee type not found for given id", "Reference", "updateCharge");
                    } else if (!optCurrency.isPresent()) {
                        throw new BadRequestAlertException("Currency not found for given id", "Reference", "updateCharge");
                    } else {
                        BnRCharge charge = optCharge.get();
                        charge.setDescription(bnRChargeDto.getDescription());
                        charge.setAmount(bnRChargeDto.getAmount());
                        charge.setEffectiveDate(bnRChargeDto.getEffectiveDate());
                        charge.setExpirationDate(bnRChargeDto.getExpirationDate());
                        charge.setBnRFeeType(optFeeType.get());
                        charge.setBnRCurrency(optCurrency.get());
                        charge = chargeRepository.save(charge);
                        return ResponseEntity.ok(chargeMapper.toDto(charge));
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateCharge");
        }
    }

    @Override
    public ResponseEntity<BnRCurrencyDto> updateCurrency(Long id, BnRCurrencyDto bnRCurrencyDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Currency id is required", "Reference", "updateCurrency");
            } else if (!id.equals(bnRCurrencyDto.getCurrencyId())) {
                throw new BadRequestAlertException("Currency id mismatch", "Reference", "updateCurrency");
            } else {
                Optional<BnRCurrency> optCurrency = currencyRepository.findById(id);
                if (!optCurrency.isPresent()) {
                    throw new BadRequestAlertException("Currency not found for given id", "Reference", "updateCurrency");
                } else {
                    BnRCurrency currency = optCurrency.get();
                    currency.setName(bnRCurrencyDto.getName());
                    currency.setCode(bnRCurrencyDto.getCode());
                    currency = currencyRepository.save(currency);
                    return ResponseEntity.ok(currencyMapper.toDto(currency));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateCurrency");
        }
    }

    @Override
    public ResponseEntity<BnRFeeTypeDto> updateFeeType(Long id, BnRFeeTypeDto bnRFeeTypeDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Fee type id is required", "Reference", "updateFeeType");
            } else if (!id.equals(bnRFeeTypeDto.getFeeTypeId())) {
                throw new BadRequestAlertException("Fee type id mismatch", "Reference", "updateFeeType");
            } else {
                Optional<BnRFeeType> optFeeType = feeTypeRepository.findById(id);
                if (!optFeeType.isPresent()) {
                    throw new BadRequestAlertException("Fee type not found for given id", "Reference", "updateFeeType");
                } else {
                    BnRFeeType feeType = optFeeType.get();
                    feeType.setName(bnRFeeTypeDto.getName());
                    feeType.setDescription(bnRFeeTypeDto.getDescription());
                    feeType = feeTypeRepository.save(feeType);
                    return ResponseEntity.ok(feeTypeMapper.toDto(feeType));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateFeeType");
        }
    }

    @Override
    public ResponseEntity<BnRIntRateDto> updateIntRate(Long id, BnRIntRateDto bnRIntRateDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Interest rate id is required", "Reference", "updateIntRate");
            } else if (!id.equals(bnRIntRateDto.getIntRateId())) {
                throw new BadRequestAlertException("Interest rate id mismatch", "Reference", "updateIntRate");
            } else {
                Optional<BnRIntRate> optIntRate = intRateRepository.findById(id);
                if (!optIntRate.isPresent()) {
                    throw new BadRequestAlertException("Interest rate not found for given id", "Reference", "updateIntRate");
                } else {
                    BnRIntRate intRate = optIntRate.get();
                    intRate.setName(bnRIntRateDto.getName());
                    intRate.setDescription(bnRIntRateDto.getDescription());
                    intRate.setRate(bnRIntRateDto.getRate());
                    intRate = intRateRepository.save(intRate);
                    return ResponseEntity.ok(intRateMapper.toDto(intRate));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateIntRate");
        }
    }

    @Override
    public ResponseEntity<BnRLoanPeriodDto> updateLoanPeriod(Long id, BnRLoanPeriodDto bnRLoanPeriodDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Loan period id is required", "Reference", "updateLoanPeriod");
            } else if (!id.equals(bnRLoanPeriodDto.getPeriodId())) {
                throw new BadRequestAlertException("Loan period id mismatch", "Reference", "updateLoanPeriod");
            } else {
                Optional<BnRLoanPeriod> optLoanPeriod = loanPeriodRepository.findById(id);
                if (!optLoanPeriod.isPresent()) {
                    throw new BadRequestAlertException("Loan period not found for given id", "Reference", "updateLoanPeriod");
                } else {
                    BnRLoanPeriod loanPeriod = optLoanPeriod.get();
                    loanPeriod.setName(bnRLoanPeriodDto.getName());
                    loanPeriod.setDescription(bnRLoanPeriodDto.getDescription());
                    loanPeriod.setMonth(bnRLoanPeriodDto.getMonth());
                    loanPeriod = loanPeriodRepository.save(loanPeriod);
                    return ResponseEntity.ok(loanPeriodMapper.toDto(loanPeriod));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateLoanPeriod");
        }
    }

    @Override
    public ResponseEntity<BnRLoanProductDto> updateLoanProduct(Long id, BnRLoanProductDto bnRLoanProductDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Loan product id is required", "Reference", "updateLoanProduct");
            } else if (!id.equals(bnRLoanProductDto.getProductId())) {
                throw new BadRequestAlertException("Loan product id mismatch", "Reference", "updateLoanProduct");
            } else {
                Optional<BnRLoanProduct> optLoanProduct = loanProductRepository.findById(id);
                if (!optLoanProduct.isPresent()) {
                    throw new BadRequestAlertException("Loan product not found for given id", "Reference", "updateLoanProduct");
                } else {
                    Optional<BnRLoanType> optLoanType = loanTypeRepository.findById(bnRLoanProductDto.getLoanTypeId());
                    Optional<BnRIntRate> optIntRate = intRateRepository.findById(bnRLoanProductDto.getIntRateId());
                    Optional<BnRLoanPeriod> optLoanPeriod = loanPeriodRepository.findById(bnRLoanProductDto.getPeriodId());
                    if (!optLoanType.isPresent()) {
                        throw new BadRequestAlertException("Loan type not found for given id", "Reference", "updateLoanProduct");
                    } else if (!optIntRate.isPresent()) {
                        throw new BadRequestAlertException("Interest rate not found for given id", "Reference", "updateLoanProduct");
                    } else if (!optLoanPeriod.isPresent()) {
                        throw new BadRequestAlertException("Loan period not found for given id", "Reference", "updateLoanProduct");
                    } else {
                        BnRLoanProduct loanProduct = optLoanProduct.get();
                        loanProduct.setBnRLoanType(optLoanType.get());
                        loanProduct.setBnRIntRate(optIntRate.get());
                        loanProduct.setBnRLoanPeriod(optLoanPeriod.get());
                        loanProduct = loanProductRepository.save(loanProduct);
                        return ResponseEntity.ok(loanProductMapper.toDto(loanProduct));
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateLoanProduct");
        }
    }

    @Override
    public ResponseEntity<BnRLoanTypeDto> updateLoanType(Long id, BnRLoanTypeDto bnRLoanTypeDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Loan type id is required", "Reference", "updateLoanType");
            } else if (!id.equals(bnRLoanTypeDto.getLoanTypeId())) {
                throw new BadRequestAlertException("Loan type id mismatch", "Reference", "updateLoanType");
            } else {
                Optional<BnRLoanType> optLoanType = loanTypeRepository.findById(id);
                if (!optLoanType.isPresent()) {
                    throw new BadRequestAlertException("Loan type not found for given id", "Reference", "updateLoanType");
                } else {
                    BnRLoanType loanType = optLoanType.get();
                    loanType.setName(bnRLoanTypeDto.getName());
                    loanType.setCode(bnRLoanTypeDto.getCode());
                    loanType.setDescription(bnRLoanTypeDto.getDescription());
                    loanType = loanTypeRepository.save(loanType);
                    return ResponseEntity.ok(loanTypeMapper.toDto(loanType));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateLoanType");
        }
    }

    @Override
    public ResponseEntity<BnRStatusDto> updateStatus(Long id, BnRStatusDto bnRStatusDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Status id is required", "Reference", "updateStatus");
            } else if (!id.equals(bnRStatusDto.getStatusId())) {
                throw new BadRequestAlertException("Status id mismatch", "Reference", "updateStatus");
            } else {
                Optional<BnRStatus> optStatus = statusRepository.findById(id);
                if (!optStatus.isPresent()) {
                    throw new BadRequestAlertException("Status not found for given id", "Reference", "updateStatus");
                } else {
                    BnRStatus status = optStatus.get();
                    status.setName(bnRStatusDto.getName());
                    status.setCode(bnRStatusDto.getCode());
                    status.setType(bnRStatusDto.getType());
                    status = statusRepository.save(status);
                    return ResponseEntity.ok(statusMapper.toDto(status));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateStatus");
        }
    }

    @Override
    public ResponseEntity<BnRTranTypeDto> updateTranType(Long id, BnRTranTypeDto bnRTranTypeDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Transaction type id is required", "Reference", "updateTranType");
            } else if (!id.equals(bnRTranTypeDto.getTranTypeId())) {
                throw new BadRequestAlertException("Transaction type id mismatch", "Reference", "updateTranType");
            } else {
                Optional<BnRTranType> optTranType = tranTypeRepository.findById(id);
                if (!optTranType.isPresent()) {
                    throw new BadRequestAlertException("Transaction type not found for given id", "Reference", "updateTranType");
                } else {
                    BnRTranType tranType = optTranType.get();
                    tranType.setName(bnRTranTypeDto.getName());
                    tranType.setCode(bnRTranTypeDto.getCode());
                    tranType = tranTypeRepository.save(tranType);
                    return ResponseEntity.ok(tranTypeMapper.toDto(tranType));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateTranType");
        }
    }

    @Override
    public ResponseEntity<BnRRoleDto> updateRole(Long id, BnRRoleDto bnRRoleDto) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Role id is required", "Reference", "updateRole");
            } else if (!id.equals(bnRRoleDto.getRoleId())) {
                throw new BadRequestAlertException("Role id mismatch", "Reference", "updateRole");
            } else {
                Optional<BnRRole> optRole = roleRepository.findById(id);
                if (!optRole.isPresent()) {
                    throw new BadRequestAlertException("Role not found for given id", "Reference", "updateRole");
                } else {
                    BnRRole role = optRole.get();
                    role.setRoleName(bnRRoleDto.getRoleName());
                    role.setDescription(bnRRoleDto.getDescription());
                    role.setIsActive(bnRRoleDto.getIsActive());
                    role = roleRepository.save(role);
                    return ResponseEntity.ok(roleMapper.toDto(role));
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "updateRole");
        }
    }

    @Override
    public ResponseEntity<String> deleteAccountType(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Account type id is required", "Reference", "deleteAccountType");
            } else {
                Optional<BnRAccountType> optAccountType = accountTypeRepository.findById(id);
                if (!optAccountType.isPresent()) {
                    throw new BadRequestAlertException("Account type not found for given id", "Reference", "deleteAccountType");
                } else {
                    accountTypeRepository.deleteById(id);
                    if (accountTypeRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Account type delete request failed");
                    } else {
                        return ResponseEntity.ok("Account type deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteAccountType");
        }
    }

    @Override
    public ResponseEntity<String> deleteBank(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Bank id is required", "Reference", "deleteBank");
            } else {
                Optional<BnRBank> optBank = bankRepository.findById(id);
                if (!optBank.isPresent()) {
                    throw new BadRequestAlertException("Bank not found for given id", "Reference", "deleteBank");
                } else {
                    bankRepository.deleteById(id);
                    if (bankRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Bank delete request failed");
                    } else {
                        return ResponseEntity.ok("Bank deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteBank");
        }
    }

    @Override
    public ResponseEntity<String> deleteBranch(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Branch id is required", "Reference", "deleteBranch");
            } else {
                Optional<BnRBranch> optBranch = branchRepository.findById(id);
                if (!optBranch.isPresent()) {
                    throw new BadRequestAlertException("Branch not found for given id", "Reference", "deleteBranch");
                } else {
                    branchRepository.deleteById(id);
                    if (branchRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Branch delete request failed");
                    } else {
                        return ResponseEntity.ok("Branch deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteBranch");
        }
    }

    @Override
    public ResponseEntity<String> deleteCharge(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Charge id is required", "Reference", "deleteCharge");
            } else {
                Optional<BnRCharge> optCharge = chargeRepository.findById(id);
                if (!optCharge.isPresent()) {
                    throw new BadRequestAlertException("Charge not found for given id", "Reference", "deleteCharge");
                } else {
                    chargeRepository.deleteById(id);
                    if (chargeRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Charge delete request failed");
                    } else {
                        return ResponseEntity.ok("Charge deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteCharge");
        }
    }

    @Override
    public ResponseEntity<String> deleteCurrency(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Currency id is required", "Reference", "deleteCurrency");
            } else {
                Optional<BnRCurrency> optCurrency = currencyRepository.findById(id);
                if (!optCurrency.isPresent()) {
                    throw new BadRequestAlertException("Currency not found for given id", "Reference", "deleteCurrency");
                } else {
                    currencyRepository.deleteById(id);
                    if (currencyRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Currency delete request failed");
                    } else {
                        return ResponseEntity.ok("Currency deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteCurrency");
        }
    }

    @Override
    public ResponseEntity<String> deleteFeeType(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Fee type id is required", "Reference", "deleteFeeType");
            } else {
                Optional<BnRFeeType> optFeeType = feeTypeRepository.findById(id);
                if (!optFeeType.isPresent()) {
                    throw new BadRequestAlertException("Fee type not found for given id", "Reference", "deleteFeeType");
                } else {
                    feeTypeRepository.deleteById(id);
                    if (feeTypeRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Fee type delete request failed");
                    } else {
                        return ResponseEntity.ok("Fee type deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteFeeType");
        }
    }

    @Override
    public ResponseEntity<String> deleteIntRate(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Interest rate id is required", "Reference", "deleteIntRate");
            } else {
                Optional<BnRIntRate> optIntRate = intRateRepository.findById(id);
                if (!optIntRate.isPresent()) {
                    throw new BadRequestAlertException("Interest rate not found for given id", "Reference", "deleteIntRate");
                } else {
                    intRateRepository.deleteById(id);
                    if (intRateRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Interest rate delete request failed");
                    } else {
                        return ResponseEntity.ok("Interest rate deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteIntRate");
        }
    }

    @Override
    public ResponseEntity<String> deleteLoanPeriod(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Loan period id is required", "Reference", "deleteLoanPeriod");
            } else {
                Optional<BnRLoanPeriod> optLoanPeriod = loanPeriodRepository.findById(id);
                if (!optLoanPeriod.isPresent()) {
                    throw new BadRequestAlertException("Loan period not found for given id", "Reference", "deleteLoanPeriod");
                } else {
                    loanPeriodRepository.deleteById(id);
                    if (loanPeriodRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Loan period delete request failed");
                    } else {
                        return ResponseEntity.ok("Loan period deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteLoanPeriod");
        }
    }

    @Override
    public ResponseEntity<String> deleteLoanProduct(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Loan product id is required", "Reference", "deleteLoanProduct");
            } else {
                Optional<BnRLoanProduct> optLoanProduct = loanProductRepository.findById(id);
                if (!optLoanProduct.isPresent()) {
                    throw new BadRequestAlertException("Loan product not found for given id", "Reference", "deleteLoanProduct");
                } else {
                    loanProductRepository.deleteById(id);
                    if (loanProductRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Loan product delete request failed");
                    } else {
                        return ResponseEntity.ok("Loan product deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteLoanProduct");
        }
    }

    @Override
    public ResponseEntity<String> deleteLoanType(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Loan type id is required", "Reference", "deleteLoanType");
            } else {
                Optional<BnRLoanType> optLoanType = loanTypeRepository.findById(id);
                if (!optLoanType.isPresent()) {
                    throw new BadRequestAlertException("Loan type not found for given id", "Reference", "deleteLoanType");
                } else {
                    loanTypeRepository.deleteById(id);
                    if (loanTypeRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Loan type delete request failed");
                    } else {
                        return ResponseEntity.ok("Loan type deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteLoanType");
        }
    }

    @Override
    public ResponseEntity<String> deleteStatus(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Status id is required", "Reference", "deleteStatus");
            } else {
                Optional<BnRStatus> optStatus = statusRepository.findById(id);
                if (!optStatus.isPresent()) {
                    throw new BadRequestAlertException("Status not found for given id", "Reference", "deleteStatus");
                } else {
                    statusRepository.deleteById(id);
                    if (statusRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Status delete request failed");
                    } else {
                        return ResponseEntity.ok("Status deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteStatus");
        }
    }

    @Override
    public ResponseEntity<String> deleteTranType(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Transaction type id is required", "Reference", "deleteTranType");
            } else {
                Optional<BnRTranType> optTranType = tranTypeRepository.findById(id);
                if (!optTranType.isPresent()) {
                    throw new BadRequestAlertException("Transaction type not found for given id", "Reference", "deleteTranType");
                } else {
                    tranTypeRepository.deleteById(id);
                    if (tranTypeRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Transaction type delete request failed");
                    } else {
                        return ResponseEntity.ok("Transaction type deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteTranType");
        }
    }

    @Override
    public ResponseEntity<String> deleteRole(Long id) {
        try {
            if (id == null) {
                throw new BadRequestAlertException("Role id is required", "Reference", "deleteRole");
            } else {
                Optional<BnRRole> optRole = roleRepository.findById(id);
                if (!optRole.isPresent()) {
                    throw new BadRequestAlertException("Role not found for given id", "Reference", "deleteRole");
                } else {
                    roleRepository.deleteById(id);
                    if (roleRepository.findById(id).isPresent()) {
                        return ResponseEntity.ok("Role delete request failed");
                    } else {
                        return ResponseEntity.ok("Role deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "Reference", "deleteRole");
        }
    }
}