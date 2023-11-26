package com.projects.smartbankingapi.rest.transaction;

import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.BankDepositTranCreateReqDto;
import com.projects.smartbankingapi.dto.other.DebitTranCreateReqDto;
import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import com.projects.smartbankingapi.service.transaction.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/bank-deposit-transaction")
    public ResponseEntity<BnTTranDto> createBankDepositTransaction(@Valid @RequestBody BankDepositTranCreateReqDto bankDepositTranCreateReqDto) {
        ResponseEntity<BnTTranDto> response = transactionService.createBankDepositTransaction(bankDepositTranCreateReqDto);
        return response;
    }

    @PostMapping("/debit-transaction")
    public ResponseEntity<BnTTranDto> createDebitTransaction(@Valid @RequestBody DebitTranCreateReqDto debitTranCreateReqDto) {
        ResponseEntity<BnTTranDto> response = transactionService.createDebitTransaction(debitTranCreateReqDto);
        return response;
    }

    @GetMapping("/{tranId}")
    public ResponseEntity<BnTTranDto> getTransaction(@PathVariable Long tranId) {
        ResponseEntity<BnTTranDto> response = transactionService.getTransaction(tranId);
        return response;
    }

    @GetMapping("/table")
    public ResponseEntity<ApiResponseDto<List<BnTTranDto>>> getTransactionsForTable(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer perPage,
            @RequestParam(defaultValue = "asc", required = false) String direction,
            @RequestParam(defaultValue = "tranId", required = false) String sort,
            @RequestParam(defaultValue = "", required = false) String search,
            @RequestParam(defaultValue = "", required = false) String fromAccountNo,
            @RequestParam(defaultValue = "", required = false) String toAccountNo,
            @RequestParam(defaultValue = "",required = false) LocalDate fromDate,
            @RequestParam(defaultValue = "",required = false) LocalDate toDate
    ) {
        ResponseEntity<ApiResponseDto<List<BnTTranDto>>> response = transactionService.getTransactionsForTable(page, perPage, direction, sort, search, fromAccountNo, toAccountNo, fromDate,toDate);
        return response;
    }

}
