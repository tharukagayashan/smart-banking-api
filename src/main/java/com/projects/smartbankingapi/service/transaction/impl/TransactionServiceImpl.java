package com.projects.smartbankingapi.service.transaction.impl;

import com.projects.smartbankingapi.dao.transaction.BnTTranRepository;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.miscellaneous.PaginationDto;
import com.projects.smartbankingapi.dto.other.BankDepositTranCreateReqDto;
import com.projects.smartbankingapi.dto.other.DebitTranCreateReqDto;
import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.transaction.BnTTranMapper;
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
}
