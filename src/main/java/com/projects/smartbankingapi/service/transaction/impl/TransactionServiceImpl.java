package com.projects.smartbankingapi.service.transaction.impl;

import com.projects.smartbankingapi.dao.master.BnMAccountRepository;
import com.projects.smartbankingapi.dao.reference.*;
import com.projects.smartbankingapi.dao.transaction.BnTTranRepository;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.miscellaneous.PaginationDto;
import com.projects.smartbankingapi.dto.other.*;
import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.transaction.BnTTranMapper;
import com.projects.smartbankingapi.model.master.BnMAccount;
import com.projects.smartbankingapi.model.transaction.BnTTran;
import com.projects.smartbankingapi.other.CustomMethods;
import com.projects.smartbankingapi.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    private final BnRStatusRepository statusRepo;
    private final BnRTranTypeRepository tranTypeRepo;
    private final CustomMethods customMethods;
    private final BnTTranRepository tranRepo;
    private final BnTTranMapper tranMapper;
    private final BnMAccountRepository accountRepo;
    private final BnRBranchRepository branchRepo;
    private final BnRCurrencyRepository currencyRepo;
    private final BnRCurrencyRateRepository currencyRateRepo;

    public TransactionServiceImpl(BnRStatusRepository statusRepo, BnRTranTypeRepository tranTypeRepo, CustomMethods customMethods, BnTTranRepository tranRepo, BnTTranMapper tranMapper, BnMAccountRepository accountRepo, BnRBranchRepository branchRepo, BnRCurrencyRepository currencyRepo, BnRCurrencyRateRepository currencyRateRepo) {
        this.statusRepo = statusRepo;
        this.tranTypeRepo = tranTypeRepo;
        this.customMethods = customMethods;
        this.tranRepo = tranRepo;
        this.tranMapper = tranMapper;
        this.accountRepo = accountRepo;
        this.branchRepo = branchRepo;
        this.currencyRepo = currencyRepo;
        this.currencyRateRepo = currencyRateRepo;
    }


    @Override
    public ResponseEntity<BnTTranDto> createBankDepositTransaction(BankDepositTranCreateReqDto bankDepositTranCreateReqDto) {
        try {
            BnTTran createdTran = customMethods.createBankDepositTransaction(bankDepositTranCreateReqDto, accountRepo, tranTypeRepo, statusRepo, tranRepo, branchRepo);
            if (createdTran == null) {
                throw new BadRequestAlertException("Error occurred while creating bank deposit transaction", "Transaction", "bank-deposit-transaction-error");
            } else {
                BnTTranDto response = tranMapper.toDto(createdTran);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating bank deposit transaction: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Transaction", "bank-deposit-transaction-error");
        }
    }

    @Override
    public ResponseEntity<BnTTranDto> createDebitTransaction(DebitTranCreateReqDto debitTranCreateReqDto) {
        try {
            BnTTran createdTran = customMethods.createDebitFundTransaction(debitTranCreateReqDto, accountRepo, tranTypeRepo, statusRepo, tranRepo, branchRepo);
            if (createdTran == null) {
                throw new BadRequestAlertException("Error occurred while creating debit transaction", "Transaction", "debit-transaction-error");
            } else {
                BnTTranDto response = tranMapper.toDto(createdTran);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating debit transaction: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Transaction", "debit-transaction-error");
        }
    }

    @Override
    public ResponseEntity<BnTTranDto> getTransaction(Long tranId) {
        try {
            if (tranId == null) {
                throw new BadRequestAlertException("tranId is required", "Transacton", "ERROR");
            } else {
                Optional<BnTTran> optTran = tranRepo.findById(tranId);
                if (!optTran.isPresent()) {
                    log.error("Transaction not found");
                    throw new BadRequestAlertException("Transaction not found", "Transaction", "ERROR");
                } else {
                    return ResponseEntity.ok(tranMapper.toDto(optTran.get()));
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while getting transaction: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Transaction", "ERROR");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<BnTTranDto>>> getTransactionsForTable(Integer page, Integer perPage, String direction, String sort, String search, String fromAccountNo, String toAccountNo, LocalDate fromDate, LocalDate toDate) {
        try {
            Page<BnTTran> dbData = null;
            if (direction.equalsIgnoreCase("asc")) {
                dbData = tranRepo.findByFilter(search, fromAccountNo, toAccountNo, PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort)));
            } else {
                dbData = tranRepo.findByFilter(search, fromAccountNo, toAccountNo, PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort)));
            }

            List<BnTTran> dbDataList = dbData.getContent();
            for (BnTTran tran : dbData.getContent()) {
                if (!tran.getTranDate().isAfter(fromDate) && !tran.getTranDate().isBefore(toDate)) {
                    dbDataList.remove(tran);
                }
            }

            ApiResponseDto<List<BnTTranDto>> response = new ApiResponseDto<>();
            PaginationDto pagination = new PaginationDto();
            pagination.setTotal(dbData.getTotalElements());
            pagination.setPerPage(perPage);
            pagination.setCurrentPage(page);
            pagination.setFrom((page * perPage) + 1);
            pagination.setTo((page * perPage) + dbData.getNumberOfElements());
            response.setPagination(pagination);
            response.setResult(tranMapper.entityListToDtoList(dbDataList));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while getting transaction: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Transaction", "ERROR");
        }
    }

    @Override
    public ResponseEntity<TransactionReceiptDto> getTranStatement(Long tranId) {
        try {
            if (tranId == null) {
                throw new BadRequestAlertException("tranId is required", "Transacton", "ERROR");
            } else {
                Optional<BnTTran> optTran = tranRepo.findById(tranId);
                if (!optTran.isPresent()) {
                    log.error("Transaction not found");
                    throw new BadRequestAlertException("Transaction not found", "Transaction", "ERROR");
                } else {

                    BnMAccount fromAccount;
                    BnMAccount toAccount;
                    BnTTran tran = optTran.get();
                    Optional<BnMAccount> optFromAccount = accountRepo.findByAccountNo(tran.getFromAccountNo());
                    Optional<BnMAccount> optToAccount = accountRepo.findByAccountNo(tran.getToAccountNo());
                    if (!optFromAccount.isPresent()) {
                        throw new BadRequestAlertException("From Account not found", "Transaction", "ERROR");
                    } else if (!optToAccount.isPresent()) {
                        throw new BadRequestAlertException("To Account not found", "Transaction", "ERROR");
                    } else {
                        log.info("From and To Account found");
                        fromAccount = optFromAccount.get();
                        toAccount = optToAccount.get();
                    }

                    TransactionReceiptDto response = new TransactionReceiptDto();
                    response.setTransactionType(tran.getBnRTranType().getName());
                    response.setTransactionDate(tran.getTranDate());
                    response.setTransactionTime(tran.getTranTime());
                    response.setTransactionId(tran.getTranId());
                    response.setFromAccountNo(tran.getFromAccountNo());
                    response.setToAccountNo(tran.getToAccountNo());
                    response.setFromAccountName(fromAccount.getBnMCustomer().getFirstName() + " " + fromAccount.getBnMCustomer().getLastName());
                    response.setToAccountName(toAccount.getBnMCustomer().getFirstName() + " " + toAccount.getBnMCustomer().getLastName());
                    response.setFromAccountBalance(fromAccount.getAvailableBalance());
                    response.setTranAmount(tran.getAmount());
                    response.setNarration(tran.getTranReference());
                    response.setBranchName(tran.getBnRBranch().getName());
                    response.setTranStatus(tran.getBnRStatus().getName());
                    response.setTranCode(tran.getBnRTranType().getCode());
                    response.setTranReference(tran.getTranReference());
                    return ResponseEntity.ok(response);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while getting transaction statement: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Transaction", "ERROR");
        }
    }

    @Override
    public ResponseEntity<BnTTranDto> createBankWithdrawTransaction(BankWithdrawReqDto bankWithdrawReqDto) {
        try {
            BnTTran createdTran = customMethods.createBankWithdrawTransaction(bankWithdrawReqDto, accountRepo, tranRepo, tranTypeRepo, statusRepo, branchRepo);
            if (createdTran.getTranId() == null) {
                throw new BadRequestAlertException("Error occurred while creating bank withdraw transaction", "Transaction", "ERROR");
            } else {
                BnTTranDto response = tranMapper.toDto(createdTran);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating bank withdraw transaction: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Transaction", "ERROR");
        }
    }

    @Override
    public ResponseEntity<BnTTranDto> createForeignCurrencyDepositTransaction(ForeignCurrencyDepositReqDto foreignCurrencyDepositReqDto) {
        try {
            BnTTran createdTran = customMethods.createForeignCurrencyDepositTransaction(foreignCurrencyDepositReqDto, accountRepo, tranRepo, tranTypeRepo, statusRepo, currencyRepo, currencyRateRepo, branchRepo);
            if (createdTran.getTranId() == null) {
                throw new BadRequestAlertException("Error occurred while creating foreign currency deposit transaction", "Transaction", "ERROR");
            } else {
                BnTTranDto response = tranMapper.toDto(createdTran);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating foreign currency deposit transaction: ", e);
            throw new BadRequestAlertException(e.getMessage(), "Transaction", "ERROR");
        }
    }
}
