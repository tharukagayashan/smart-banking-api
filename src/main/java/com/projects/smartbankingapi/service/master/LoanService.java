package com.projects.smartbankingapi.service.master;

import com.projects.smartbankingapi.dto.master.BnMLoanDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.other.LoanCreateReqDto;
import com.projects.smartbankingapi.dto.other.LoanDisburseReqDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoanService {
    ResponseEntity<BnMLoanDto> createLoan(LoanCreateReqDto loanCreateReqDto);

    ResponseEntity<BnMLoanDto> approveLoan(Long loanId);

    ResponseEntity<BnMLoanDto> getLoan(Long loanId);

    ResponseEntity<ApiResponseDto<List<BnMLoanDto>>> getLoanForTable(Integer page, Integer perPage, String sort, String direction, String search);

    ResponseEntity<BnMLoanDto> disburseLoan(Long loanId, LoanDisburseReqDto loanDisburseReqDto);
}
