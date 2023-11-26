package com.projects.smartbankingapi.rest.master;

import com.projects.smartbankingapi.dto.master.BnMLoanDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.LoanCreateReqDto;
import com.projects.smartbankingapi.service.master.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/create")
    public ResponseEntity<BnMLoanDto> createLoan(@RequestBody LoanCreateReqDto loanCreateReqDto) {
        ResponseEntity<BnMLoanDto> response = loanService.createLoan(loanCreateReqDto);
        return response;
    }

    @PutMapping("/approve/{loanId}")
    public ResponseEntity<BnMLoanDto> approveLoan(@PathVariable Long loanId) {
        ResponseEntity<BnMLoanDto> response = loanService.approveLoan(loanId);
        return response;
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<BnMLoanDto> getLoan(@PathVariable Long loanId) {
        ResponseEntity<BnMLoanDto> response = loanService.getLoan(loanId);
        return response;
    }

    @GetMapping("/table")
    public ResponseEntity<ApiResponseDto<List<BnMLoanDto>>> getLoanForTable(
            @RequestParam(defaultValue = "0",required = false) Integer page,
            @RequestParam(defaultValue = "10",required = false) Integer perPage,
            @RequestParam(defaultValue = "loanId",required = false) String sort,
            @RequestParam(defaultValue = "desc",required = false) String direction,
            @RequestParam(defaultValue = "",required = false) String search
    ) {
        ResponseEntity<ApiResponseDto<List<BnMLoanDto>>> response = loanService.getLoanForTable(page, perPage, sort, direction, search);
        return response;
    }

}
