package com.projects.smartbankingapi.service.transaction.impl;

import com.projects.smartbankingapi.dao.transaction.BnTTranRepository;
import com.projects.smartbankingapi.dto.other.BankDepositTranCreateReqDto;
import com.projects.smartbankingapi.dto.other.DebitTranCreateReqDto;
import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.transaction.BnTTranMapper;
import com.projects.smartbankingapi.model.transaction.BnTTran;
import com.projects.smartbankingapi.other.CustomMethods;
import com.projects.smartbankingapi.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final CustomMethods customMethods;
    private final BnTTranRepository tranRepo;
    private final BnTTranMapper tranMapper;

    public TransactionServiceImpl(CustomMethods customMethods, BnTTranRepository tranRepo, BnTTranMapper tranMapper) {
        this.customMethods = customMethods;
        this.tranRepo = tranRepo;
        this.tranMapper = tranMapper;
    }

    @Override
    public ResponseEntity<BnTTranDto> createBankDepositTransaction(BankDepositTranCreateReqDto bankDepositTranCreateReqDto) {
        try {
            BnTTran createdTran = customMethods.createBankDepositTransaction(bankDepositTranCreateReqDto);
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
            BnTTran createdTran = customMethods.createDebitFundTransaction(debitTranCreateReqDto);
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
}
