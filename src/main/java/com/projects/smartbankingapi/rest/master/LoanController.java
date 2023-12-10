package com.projects.smartbankingapi.rest.master;

import com.projects.smartbankingapi.dto.master.BnMLoanDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.CalculatorReqDto;
import com.projects.smartbankingapi.dto.other.CalculatorResponseDto;
import com.projects.smartbankingapi.dto.other.LoanCreateReqDto;
import com.projects.smartbankingapi.dto.other.LoanDisburseReqDto;
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
        return loanService.createLoan(loanCreateReqDto);
    }

    @PutMapping("/approve/{loanId}")
    public ResponseEntity<BnMLoanDto> approveLoan(@PathVariable Long loanId) {
        return loanService.approveLoan(loanId);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<BnMLoanDto> getLoan(@PathVariable Long loanId) {
        return loanService.getLoan(loanId);
    }

    @GetMapping("/table")
    public ResponseEntity<ApiResponseDto<List<BnMLoanDto>>> getLoanForTable(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer perPage,
            @RequestParam(defaultValue = "loanId", required = false) String sort,
            @RequestParam(defaultValue = "desc", required = false) String direction,
            @RequestParam(defaultValue = "", required = false) String search
    ) {
        return loanService.getLoanForTable(page, perPage, sort, direction, search);
    }

    @PutMapping("/disburse/{loanId}")
    public ResponseEntity<BnMLoanDto> disburseLoan(@PathVariable Long loanId, @RequestBody LoanDisburseReqDto loanDisburseReqDto) {
        return loanService.disburseLoan(loanId, loanDisburseReqDto);
    }

    @PostMapping("/calculator")
    public ResponseEntity<CalculatorResponseDto> calculator(@RequestBody CalculatorReqDto calculatorReqDto){
        return loanService.calculator(calculatorReqDto);
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long loanId) {
        return loanService.deleteLoan(loanId);
    }

}
