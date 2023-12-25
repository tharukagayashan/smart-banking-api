package com.projects.smartbankingapi.service.master.impl;

import com.projects.smartbankingapi.constant.HardCodeConstant;
import com.projects.smartbankingapi.dao.master.BnMAccountRepository;
import com.projects.smartbankingapi.dao.master.BnMCustomerRepository;
import com.projects.smartbankingapi.dao.reference.BnRAccountTypeRepository;
import com.projects.smartbankingapi.dao.reference.BnRBranchRepository;
import com.projects.smartbankingapi.dao.reference.BnRCurrencyRepository;
import com.projects.smartbankingapi.dao.reference.BnRStatusRepository;
import com.projects.smartbankingapi.dto.master.BnMAccountDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.miscellaneous.PaginationDto;
import com.projects.smartbankingapi.dto.other.AccountCreateReqDto;
import com.projects.smartbankingapi.dto.other.ResponseDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.master.BnMAccountMapper;
import com.projects.smartbankingapi.model.master.BnMAccount;
import com.projects.smartbankingapi.model.master.BnMCustomer;
import com.projects.smartbankingapi.model.reference.BnRAccountType;
import com.projects.smartbankingapi.model.reference.BnRBranch;
import com.projects.smartbankingapi.model.reference.BnRCurrency;
import com.projects.smartbankingapi.model.reference.BnRStatus;
import com.projects.smartbankingapi.other.CustomMethods;
import com.projects.smartbankingapi.service.master.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    private final BnMAccountRepository accountRepository;
    private final BnMAccountMapper accountMapper;
    private final BnMCustomerRepository customerRepository;
    private final BnRCurrencyRepository currencyRepository;
    private final BnRBranchRepository branchRepository;
    private final BnRAccountTypeRepository accountTypeRepository;
    private final BnRStatusRepository statusRepository;
    private final CustomMethods customMethods;

    public AccountServiceImpl(BnMAccountRepository accountRepository, BnMAccountMapper accountMapper, BnMCustomerRepository customerRepository, BnRCurrencyRepository currencyRepository, BnRBranchRepository branchRepository, BnRAccountTypeRepository accountTypeRepository, BnRStatusRepository statusRepository, CustomMethods customMethods) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.customerRepository = customerRepository;
        this.currencyRepository = currencyRepository;
        this.branchRepository = branchRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.statusRepository = statusRepository;
        this.customMethods = customMethods;
    }

    @Override
    public ResponseEntity<ResponseDto> createAccount(AccountCreateReqDto accountCreateReqDto) {
        try {
            BnMAccount account = getBnMAccount(accountCreateReqDto);
            Optional<BnMAccount> optAccount = accountRepository.findByBnMCustomerNicAndBnRAccountTypeAccountTypeId(account.getBnMCustomer().getNic(), account.getBnRAccountType().getAccountTypeId());
            if (optAccount.isPresent()) {
                throw new BadRequestAlertException("Account already exists for given customer nic and account type", "BnMAccount", "ALREADY_EXISTS");
            }

            account = accountRepository.save(account);
            if (account.getAccountId() == null) {
                throw new BadRequestAlertException("Error while creating account", "BnMAccount", "ERROR");
            } else {
                ResponseDto response = new ResponseDto();
                String accountNo = customMethods.generateAccountNumber(account.getBnRBranch().getCode(), account.getBnRAccountType().getCode(), account.getAccountId());
                account.setAccountNo(accountNo);
                ;
                account = accountRepository.save(account);
                BnMAccountDto accountDto = accountMapper.toDto(account);

                float minimumBalance;
                if (account.getBnRAccountType().getAccountTypeId() == HardCodeConstant.SAVING_ACCOUNT_TYPE_ID.longValue()) {
                    minimumBalance = HardCodeConstant.SAVING_MIN_BALANCE;
                } else if (account.getBnRAccountType().getAccountTypeId() == HardCodeConstant.CHECK_ACCOUNT_TYPE_ID.longValue()) {
                    minimumBalance = HardCodeConstant.CHECK_MIN_BALANCE;
                } else if (account.getBnRAccountType().getAccountTypeId() == HardCodeConstant.FIXED_ACCOUNT_TYPE_ID.longValue()) {
                    minimumBalance = HardCodeConstant.FIXED_MIN_BALANCE;
                } else {
                    throw new BadRequestAlertException("Account type not found", "BnRAccountType", "NOT_FOUND");
                }

                log.info("Account successfully created and account no is: {}", accountNo);
                log.info("Please deposit minimum amount to activate account");

                response.setData(accountDto);
                response.setMessage("Account successfully created and account no is: " + accountNo + ". Please deposit " + minimumBalance + " to activate account");
                response.setStatus(HttpStatus.OK.value());
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            log.error("Error while creating account: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMAccount", "ERROR");
        }
    }

    @Override
    public ResponseEntity<BnMAccountDto> getAccount(Long accountId) {
        try {
            if (accountId == null) {
                throw new BadRequestAlertException("Account id is required", "BnMAccount", "REQUIRED");
            } else {
                Optional<BnMAccount> optAccount = accountRepository.findById(accountId);
                if (!optAccount.isPresent()) {
                    throw new BadRequestAlertException("Account not found", "BnMAccount", "NOT_FOUND");
                } else {
                    BnMAccountDto accountDto = accountMapper.toDto(optAccount.get());
                    return ResponseEntity.ok(accountDto);
                }
            }
        } catch (Exception e) {
            log.error("Error while getting account: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMAccount", "ERROR");
        }
    }

    @Override
    public ResponseEntity<List<BnMAccountDto>> getAllAccounts() {
        try {
            List<BnMAccount> accounts = accountRepository.findAll();
            if (accounts.isEmpty()) {
                throw new BadRequestAlertException("No accounts found", "BnMAccount", "NOT_FOUND");
            } else {
                List<BnMAccountDto> accountDtos = accountMapper.entityListToDtoList(accounts);
                return ResponseEntity.ok(accountDtos);
            }
        } catch (Exception e) {
            log.error("Error while getting all accounts: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMAccount", "ERROR");
        }
    }

    @Override
    public ResponseEntity<BnMAccountDto> updateAccount(Long accountId, BnMAccountDto bnMAccountDto) {
        try {
            if (!Objects.equals(accountId, bnMAccountDto.getAccountId())) {
                throw new BadRequestAlertException("Account id mismatch", "BnMAccount", "MISMATCH");
            } else {
                ResponseEntity<BnMAccountDto> accountDtoResponseEntity = getAccount(accountId);
                if (accountDtoResponseEntity.getStatusCode().isError()) {
                    throw new BadRequestAlertException("Account not found", "BnMAccount", "NOT_FOUND");
                } else {
                    BnMAccount updatedAccount = getBnMAccount(accountMapper.toEntity(accountDtoResponseEntity.getBody()), bnMAccountDto);
                    updatedAccount = accountRepository.save(updatedAccount);
                    BnMAccountDto updatedAccountDto = accountMapper.toDto(updatedAccount);
                    return ResponseEntity.ok(updatedAccountDto);
                }
            }
        } catch (Exception e) {
            log.error("Error while updating account: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMAccount", "ERROR");
        }
    }

    @Override
    public ResponseEntity<String> deleteAccount(Long accountId) {
        try {
            if (accountId == null) {
                throw new BadRequestAlertException("Account id is required", "BnMAccount", "REQUIRED");
            } else {
                ResponseEntity<BnMAccountDto> accountDtoRes = getAccount(accountId);
                if (accountDtoRes.getStatusCode() != HttpStatus.OK) {
                    throw new BadRequestAlertException("Account not found", "BnMAccount", "NOT_FOUND");
                } else {
                    accountRepository.deleteById(accountId);
                    ResponseEntity<BnMAccountDto> accountAfterDelete = getAccount(accountId);
                    if (accountAfterDelete.getStatusCode() == HttpStatus.OK) {
                        throw new BadRequestAlertException("Error while deleting account", "BnMAccount", "ERROR");
                    } else {
                        return ResponseEntity.ok("Account successfully deleted");
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while deleting account: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMAccount", "ERROR");
        }
    }

    @Override
    public ResponseEntity<List<BnMAccountDto>> getAllAccountsByIsActive(String isActive) {
        try {
            if (isActive == null) {
                throw new BadRequestAlertException("isActive is required", "BnMAccount", "REQUIRED");
            } else {
                boolean flag;
                if (isActive.equals(HardCodeConstant.ACTIVE.toString())) {
                    flag = true;
                } else if (isActive.equals(HardCodeConstant.INACTIVE.toString())) {
                    flag = false;
                } else {
                    throw new BadRequestAlertException("isActive should be true or false", "BnMCustomer", "error");
                }
                List<BnMAccount> accounts = accountRepository.findAllByIsActive(flag);
                if (accounts.isEmpty()) {
                    throw new BadRequestAlertException("No accounts found", "BnMAccount", "NOT_FOUND");
                } else {
                    List<BnMAccountDto> accountDtos = accountMapper.entityListToDtoList(accounts);
                    return ResponseEntity.ok(accountDtos);
                }
            }
        } catch (Exception e) {
            log.error("Error while getting all accounts by isActive: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMAccount", "ERROR");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<BnMAccountDto>>> getAccountsForTable(Integer page, Integer perPage, String sort, String direction, String search) {
        try {
            Page<BnMAccount> dbData = null;
            if (direction.equalsIgnoreCase("asc")) {
                dbData = accountRepository.findAllForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort)));
            } else {
                dbData = accountRepository.findAllForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort)));
            }

            ApiResponseDto<List<BnMAccountDto>> response = new ApiResponseDto<>();
            PaginationDto pagination = new PaginationDto();
            pagination.setPerPage(perPage);
            pagination.setCurrentPage(page);
            pagination.setFrom((page * perPage) + dbData.getNumberOfElements());
            pagination.setTo((page * perPage) + dbData.getNumberOfElements());
            pagination.setTotal(dbData.getTotalElements());
            response.setPagination(pagination);
            response.setResult(accountMapper.entityListToDtoList(dbData.getContent()));
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error while getting accounts for table: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMAccount", "ERROR");
        }
    }

    @Override
    public ResponseEntity<BnMAccountDto> getAccountByAccountNo(String accountNo) {
        try {
            if (accountNo == null || accountNo.isEmpty()) {
                throw new BadRequestAlertException("Account no is required", "BnMAccount", "REQUIRED");
            } else {
                Optional<BnMAccount> optAccount = accountRepository.findByAccountNo(accountNo);
                if (!optAccount.isPresent()) {
                    throw new BadRequestAlertException("Account not found", "BnMAccount", "NOT_FOUND");
                } else {
                    BnMAccountDto accountDto = accountMapper.toDto(optAccount.get());
                    return ResponseEntity.ok(accountDto);
                }
            }
        } catch (Exception e) {
            log.error("Error while getting account by accountNo: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMAccount", "ERROR");
        }
    }

    @Override
    public ResponseEntity<String> deactivateAccount(Long accountId) {
        try {
            if (accountId == null) {
                throw new BadRequestAlertException("Account id is required", "BnMAccount", "REQUIRED");
            } else {
                ResponseEntity<BnMAccountDto> accountDtoRes = getAccount(accountId);
                if (accountDtoRes.getStatusCode() != HttpStatus.OK) {
                    throw new BadRequestAlertException("Account not found", "BnMAccount", "NOT_FOUND");
                } else {
                    BnMAccount account = accountMapper.toEntity(accountDtoRes.getBody());
                    account.setIsActive(false);
                    account = accountRepository.save(account);
                    if (!account.getIsActive()) {
                        return ResponseEntity.ok("Account deactivated");
                    } else {
                        throw new BadRequestAlertException("Error while deactivating account", "BnMAccount", "ERROR");
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while deactivating account: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "BnMAccount", "ERROR");
        }
    }

    private BnMAccount getBnMAccount(BnMAccount entity, BnMAccountDto bnMAccountDto) {
        Optional<BnMCustomer> optCustomer = customerRepository.findById(bnMAccountDto.getCustomerId());
        if (!optCustomer.isPresent()) {
            throw new BadRequestAlertException("Customer not found", "BnMCustomer", "NOT_FOUND");
        }

        Optional<BnRCurrency> optCurrency = currencyRepository.findById(bnMAccountDto.getCurrencyId());
        if (!optCurrency.isPresent()) {
            throw new BadRequestAlertException("Currency not found", "BnRCurrency", "NOT_FOUND");
        }

        Optional<BnRBranch> optBranch = branchRepository.findById(bnMAccountDto.getBranchId());
        if (!optBranch.isPresent()) {
            throw new BadRequestAlertException("Branch not found", "BnRBranch", "NOT_FOUND");
        }

        Optional<BnRAccountType> optAccountType = accountTypeRepository.findById(bnMAccountDto.getAccountTypeId());
        if (!optAccountType.isPresent()) {
            throw new BadRequestAlertException("Account type not found", "BnRAccountType", "NOT_FOUND");
        }

        Optional<BnRStatus> optStatus = statusRepository.findById(bnMAccountDto.getStatusId());
        if (!optStatus.isPresent()) {
            throw new BadRequestAlertException("Status not found", "BnRStatus", "NOT_FOUND");
        }

        entity.setAccountId(bnMAccountDto.getAccountId());
        entity.setAccountNo(bnMAccountDto.getAccountNo());
        entity.setCurrentBalance(bnMAccountDto.getCurrentBalance());
        entity.setAvailableBalance(bnMAccountDto.getAvailableBalance());
        entity.setHoldBalance(bnMAccountDto.getHoldBalance());
        entity.setOpenedDate(bnMAccountDto.getOpenedDate());
        entity.setBnMCustomer(optCustomer.get());
        entity.setBnRAccountType(optAccountType.get());
        entity.setBnRStatus(optStatus.get());
        entity.setBnRCurrency(optCurrency.get());
        entity.setBnRBranch(optBranch.get());
        entity.setIsFirstDepositDone(bnMAccountDto.getIsFirstDepositDone());
        return entity;
    }

    private BnMAccount getBnMAccount(AccountCreateReqDto accountCreateReqDto) {
        Optional<BnMCustomer> optCustomer = customerRepository.findById(accountCreateReqDto.getCustomerId());
        if (!optCustomer.isPresent()) {
            throw new BadRequestAlertException("Customer not found", "BnMCustomer", "NOT_FOUND");
        }

        Optional<BnRCurrency> optCurrency = currencyRepository.findById(accountCreateReqDto.getCurrencyId());
        if (!optCurrency.isPresent()) {
            throw new BadRequestAlertException("Currency not found", "BnRCurrency", "NOT_FOUND");
        }

        Optional<BnRBranch> optBranch = branchRepository.findById(accountCreateReqDto.getBranchId());
        if (!optBranch.isPresent()) {
            throw new BadRequestAlertException("Branch not found", "BnRBranch", "NOT_FOUND");
        }

        Optional<BnRAccountType> optAccountType = accountTypeRepository.findById(accountCreateReqDto.getAccountTypeId());
        if (!optAccountType.isPresent()) {
            throw new BadRequestAlertException("Account type not found", "BnRAccountType", "NOT_FOUND");
        }

        Optional<BnRStatus> optStatus = statusRepository.findById(HardCodeConstant.STATUS_NEW_ID.longValue());
        if (!optStatus.isPresent()) {
            throw new BadRequestAlertException("Status not found", "BnRStatus", "NOT_FOUND");
        }

        BnMAccount account = new BnMAccount();

        account.setCurrentBalance((float) 0);
        account.setAvailableBalance((float) 0);
        account.setHoldBalance((float) 0);
        account.setOpenedDate(LocalDate.now());
        account.setBnMCustomer(optCustomer.get());
        account.setBnRAccountType(optAccountType.get());
        account.setBnRCurrency(optCurrency.get());
        account.setBnRBranch(optBranch.get());
        account.setBnRStatus(optStatus.get());
        account.setIsActive(false);
        account.setIsFirstDepositDone(false);
        return account;
    }

}