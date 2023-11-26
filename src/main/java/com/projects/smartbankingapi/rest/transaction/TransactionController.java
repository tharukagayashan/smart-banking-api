package com.projects.smartbankingapi.rest.transaction;

import com.projects.smartbankingapi.dto.other.BankDepositTranCreateReqDto;
import com.projects.smartbankingapi.dto.other.DebitTranCreateReqDto;
import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import com.projects.smartbankingapi.service.transaction.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

}
