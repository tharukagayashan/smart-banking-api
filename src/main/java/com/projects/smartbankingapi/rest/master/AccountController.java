package com.projects.smartbankingapi.rest.master;

import com.projects.smartbankingapi.dto.master.BnMAccountDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.AccountCreateReqDto;
import com.projects.smartbankingapi.dto.other.ResponseDto;
import com.projects.smartbankingapi.service.master.AccountService;
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
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody AccountCreateReqDto accountCreateReqDto) {
        return accountService.createAccount(accountCreateReqDto);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<BnMAccountDto> getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BnMAccountDto>> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<BnMAccountDto> updateAccount(@PathVariable Long accountId, @Valid @RequestBody BnMAccountDto bnMAccountDto) {
        return accountService.updateAccount(accountId, bnMAccountDto);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<BnMAccountDto> deleteAccount(@PathVariable Long accountId) {
        return accountService.deleteAccount(accountId);
    }

    @GetMapping("/all/isActive/{isActive}")
    public ResponseEntity<List<BnMAccountDto>> getAllAccountsByIsActive(@PathVariable String isActive) {
        return accountService.getAllAccountsByIsActive(isActive);
    }

    @GetMapping("/table")
    public ResponseEntity<ApiResponseDto<List<BnMAccountDto>>> getAccountsForTable(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer perPage,
            @RequestParam(required = false, defaultValue = "accountId") String sort,
            @RequestParam(required = false, defaultValue = "asc") String direction,
            @RequestParam(required = false, defaultValue = "") String search
    ) {
        return accountService.getAccountsForTable(page, perPage, sort, direction, search);
    }

    @GetMapping("/accountNo/{accountNo}")
    public ResponseEntity<BnMAccountDto> getAccountByAccountNo(@PathVariable String accountNo) {
        return accountService.getAccountByAccountNo(accountNo);
    }

}