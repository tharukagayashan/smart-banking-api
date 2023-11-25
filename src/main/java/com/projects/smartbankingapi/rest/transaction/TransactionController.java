package com.projects.smartbankingapi.rest.transaction;

import com.projects.smartbankingapi.dto.other.TranCreateReqDto;
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

    @PostMapping
    public ResponseEntity<BnTTranDto> createTransaction(@Valid @RequestBody TranCreateReqDto tranCreateReqDto) {
        ResponseEntity<BnTTranDto> response = transactionService.createTransaction(tranCreateReqDto);
        return response;
    }

}
