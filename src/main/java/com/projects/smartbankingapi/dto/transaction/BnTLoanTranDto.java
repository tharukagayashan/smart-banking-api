package com.projects.smartbankingapi.dto.transaction;

import com.projects.smartbankingapi.dto.master.BnMLoanDto;
import com.projects.smartbankingapi.dto.reference.BnRLoanPayTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnTLoanTranDto {
    private Long loanTranId;
    private Float amount;
    private String description;
    private String tranReference;
    private LocalDate tranDate;
    private LocalTime tranTime;
    private BnMLoanDto bnMLoan;
    private BnRLoanPayTypeDto bnRLoanPayType;
}