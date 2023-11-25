package com.projects.smartbankingapi.rest.master;

import com.projects.smartbankingapi.dto.master.BnMAccountDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.AccountCreateReqDto;
import com.projects.smartbankingapi.model.master.BnMAccount;
import com.projects.smartbankingapi.service.master.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<BnMAccountDto> createAccount(@Valid @RequestBody AccountCreateReqDto accountCreateReqDto){
        ResponseEntity<BnMAccountDto> response = accountService.createAccount(accountCreateReqDto);
        return response;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<BnMAccountDto> getAccount(@PathVariable Long accountId){
        ResponseEntity<BnMAccountDto> response = accountService.getAccount(accountId);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BnMAccountDto>> getAllAccounts(){
        ResponseEntity<List<BnMAccountDto>> response = accountService.getAllAccounts();
        return response;
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<BnMAccountDto> updateAccount(@PathVariable Long accountId, @Valid @RequestBody BnMAccountDto bnMAccountDto){
        ResponseEntity<BnMAccountDto> response = accountService.updateAccount(accountId, bnMAccountDto);
        return response;
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<BnMAccountDto> deleteAccount(@PathVariable Long accountId){
        ResponseEntity<BnMAccountDto> response = accountService.deleteAccount(accountId);
        return response;
    }

    @GetMapping("/all/isActive/{isActive}")
    public ResponseEntity<List<BnMAccountDto>> getAllAccountsByIsActive(@PathVariable String isActive){
        ResponseEntity<List<BnMAccountDto>> response = accountService.getAllAccountsByIsActive(isActive);
        return response;
    }

    @GetMapping("/table")
    public ResponseEntity<ApiResponseDto<List<BnMAccountDto>>> getAccountsForTable(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer perPage,
            @RequestParam(required = false, defaultValue = "accountId") String sort,
            @RequestParam(required = false, defaultValue = "asc") String direction,
            @RequestParam(required = false, defaultValue = "") String search
    ){
        ResponseEntity<ApiResponseDto<List<BnMAccountDto>>> response = accountService.getAccountsForTable(page, perPage, sort, direction, search);
        return response;
    }

}