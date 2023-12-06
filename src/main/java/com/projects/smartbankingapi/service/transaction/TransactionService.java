package com.projects.smartbankingapi.service.transaction;

import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.BankDepositTranCreateReqDto;
import com.projects.smartbankingapi.dto.other.DebitTranCreateReqDto;
import com.projects.smartbankingapi.dto.other.TranCreateReqDto;
import com.projects.smartbankingapi.dto.other.TransactionReceiptDto;
import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    ResponseEntity<BnTTranDto> createBankDepositTransaction(BankDepositTranCreateReqDto bankDepositTranCreateReqDto);
    ResponseEntity<BnTTranDto> createDebitTransaction(DebitTranCreateReqDto debitTranCreateReqDto);
    ResponseEntity<BnTTranDto> getTransaction(Long tranId);
    ResponseEntity<ApiResponseDto<List<BnTTranDto>>> getTransactionsForTable(Integer page, Integer perPage, String direction, String sort, String search, String fromAccountNo, String toAccountNo, LocalDate fromDate, LocalDate toDate);
    ResponseEntity<TransactionReceiptDto> getTranStatement(Long tranId);
}
