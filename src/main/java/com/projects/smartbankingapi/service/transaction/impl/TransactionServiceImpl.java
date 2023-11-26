package com.projects.smartbankingapi.service.transaction.impl;

import com.projects.smartbankingapi.dao.master.BnMAccountRepository;
import com.projects.smartbankingapi.dao.transaction.BnTTranRepository;
import com.projects.smartbankingapi.dto.other.TranCreateReqDto;
import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.transaction.BnTTranMapper;
import com.projects.smartbankingapi.model.master.BnMAccount;
import com.projects.smartbankingapi.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final BnTTranRepository tranRepository;
    private final BnTTranMapper tranMapper;
    private final BnMAccountRepository accountRepository;

    public TransactionServiceImpl(BnTTranRepository tranRepository, BnTTranMapper tranMapper, BnMAccountRepository accountRepository) {
        this.tranRepository = tranRepository;
        this.tranMapper = tranMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<BnTTranDto> createTransaction(TranCreateReqDto tranCreateReqDto) {
        try {
            Optional<BnMAccount> optAccount = accountRepository.findById(tranCreateReqDto.getAccountId());
            if (!optAccount.isPresent()) {
                throw new BadRequestAlertException("Account not found", "account", "accountNotFound");
            } else {
                BnMAccount account = optAccount.get();

                Float availableBalance = account.getAvailableBalance();
                Float currentBalance = account.getCurrentBalance();
                Float holdBalance = account.getHoldBalance();

                if (tranCreateReqDto.getTranTypeId() == 1) {
                    availableBalance = availableBalance + tranCreateReqDto.getAmount();
                    currentBalance = currentBalance + tranCreateReqDto.getAmount();
                } else if (tranCreateReqDto.getTranTypeId() == 2) {
                    if (availableBalance < tranCreateReqDto.getAmount()) {
                        throw new BadRequestAlertException("Insufficient balance", "account", "insufficientBalance");
                    } else {
                        availableBalance = availableBalance - tranCreateReqDto.getAmount();
                        currentBalance = currentBalance - tranCreateReqDto.getAmount();
                    }
                } else if (tranCreateReqDto.getTranTypeId() == 3) {
                    if (availableBalance < tranCreateReqDto.getAmount()) {
                        throw new BadRequestAlertException("Insufficient balance", "account", "insufficientBalance");
                    } else {
                        availableBalance = availableBalance - tranCreateReqDto.getAmount();
                        holdBalance = holdBalance + tranCreateReqDto.getAmount();
                    }
                } else if (tranCreateReqDto.getTranTypeId() == 4) {
                    holdBalance = holdBalance - tranCreateReqDto.getAmount();
                    currentBalance = currentBalance + tranCreateReqDto.getAmount();
                } else {
                    throw new BadRequestAlertException("Invalid transaction type", "transaction", "invalidTransactionType");
                }

                account.setAvailableBalance(availableBalance);
                account.setCurrentBalance(currentBalance);
                account.setHoldBalance(holdBalance);
                account = accountRepository.save(account);
                if (account != null) {
                    BnTTranDto tranDto = new BnTTranDto();
                    tranDto.setAccountId(account.getAccountId());
                    tranDto.setAmount(tranCreateReqDto.getAmount());
                    tranDto.setTranTypeId(tranCreateReqDto.getTranTypeId());
                    tranDto.setTranDate(LocalDate.now());
                    tranDto.setTranTime(LocalTime.now());
                    tranDto = tranMapper.toDto(tranRepository.save(tranMapper.toEntity(tranDto)));
                    log.info("Transaction created successfully");
                    return ResponseEntity.ok(tranDto);
                } else {
                    throw new BadRequestAlertException("Error while creating transaction", "transaction", "errorCreatingTransaction");
                }
            }
        } catch (Exception e) {
            log.error("Error while creating transaction", e);
            throw new BadRequestAlertException(e.getMessage(), "transaction", "errorCreatingTransaction");
        }
    }
}
