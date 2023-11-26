package com.projects.smartbankingapi.service.master;

import com.projects.smartbankingapi.dto.master.BnMAccountDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.AccountCreateReqDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    ResponseEntity<BnMAccountDto> createAccount(AccountCreateReqDto accountCreateReqDto);

    ResponseEntity<BnMAccountDto> getAccount(Long accountId);

    ResponseEntity<List<BnMAccountDto>> getAllAccounts();

    ResponseEntity<BnMAccountDto> updateAccount(Long accountId, BnMAccountDto bnMAccountDto);

    ResponseEntity<BnMAccountDto> deleteAccount(Long accountId);

    ResponseEntity<List<BnMAccountDto>> getAllAccountsByIsActive(String isActive);

    ResponseEntity<ApiResponseDto<List<BnMAccountDto>>> getAccountsForTable(Integer page, Integer perPage, String sort, String direction, String search);
}
