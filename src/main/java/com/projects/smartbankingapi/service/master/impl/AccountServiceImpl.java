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
    public ResponseEntity<BnMAccountDto> createAccount(AccountCreateReqDto accountCreateReqDto) {
        try {
            BnMAccount account = getBnMAccount(accountCreateReqDto);

            Optional<BnMAccount> optAccount = accountRepository.findByBnMCustomerNic(account.getBnMCustomer().getNic());
            if (optAccount.isPresent()) {
                throw new BadRequestAlertException("Account already exists for given customer", "BnMAccount", "ALREADY_EXISTS");
            }

            account = accountRepository.save(account);
            if (account == null) {
                throw new BadRequestAlertException("Error while creating account", "BnMAccount", "ERROR");
            } else {
                String accountNo = customMethods.generateAccountNumber(account.getBnRBranch().getCode(), account.getBnRAccountType().getCode(), account.getAccountId());
                account.setAccountNo(accountNo);
                account = accountRepository.save(account);
                BnMAccountDto accountDto = accountMapper.toDto(account);
                return ResponseEntity.ok(accountDto);
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
            if (accountId != bnMAccountDto.getAccountId()) {
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
    public ResponseEntity<BnMAccountDto> deleteAccount(Long accountId) {
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
                    return ResponseEntity.ok(accountMapper.toDto(account));
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
                boolean flag = false;
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

        BnMAccount account = entity;
        account.setAccountId(bnMAccountDto.getAccountId());
        account.setAccountNo(bnMAccountDto.getAccountNo());
        account.setCurrentBalance(bnMAccountDto.getCurrentBalance());
        account.setAvailableBalance(bnMAccountDto.getAvailableBalance());
        account.setHoldBalance(bnMAccountDto.getHoldBalance());
        account.setOpenedDate(bnMAccountDto.getOpenedDate());
        account.setBnMCustomer(optCustomer.get());
        account.setBnRAccountType(optAccountType.get());
        account.setBnRStatus(optStatus.get());
        account.setBnRCurrency(optCurrency.get());
        account.setBnRBranch(optBranch.get());
        return account;
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

        Optional<BnRStatus> optStatus = statusRepository.findById(HardCodeConstant.STATUS_NEW.longValue());
        if (!optStatus.isPresent()) {
            throw new BadRequestAlertException("Status not found", "BnRStatus", "NOT_FOUND");
        }

        BnMAccount account = new BnMAccount();

        account.setCurrentBalance(new Float(0));
        account.setAvailableBalance(new Float(0));
        account.setHoldBalance(new Float(0));
        account.setOpenedDate(LocalDate.now());
        account.setBnMCustomer(optCustomer.get());
        account.setBnRAccountType(optAccountType.get());
        account.setBnRCurrency(optCurrency.get());
        account.setBnRBranch(optBranch.get());
        account.setBnRStatus(optStatus.get());
        account.setIsActive(true);
        return account;
    }

}