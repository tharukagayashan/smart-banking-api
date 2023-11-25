package com.projects.smartbankingapi.service.transaction;

import com.projects.smartbankingapi.dto.other.TranCreateReqDto;
import com.projects.smartbankingapi.dto.transaction.BnTTranDto;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<BnTTranDto> createTransaction(TranCreateReqDto tranCreateReqDto);
}
