package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionReceiptDto {

    private String transactionType;
    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private Long transactionId;
    private String fromAccountNo;
    private String toAccountNo;
    private String fromAccountName;
    private String toAccountName;
    private Float fromAccountBalance;
    private Float tranAmount;
    private String narration;
    private String branchName;
    private String tranStatus;
    private String tranCode;
    private String tranReference;

}