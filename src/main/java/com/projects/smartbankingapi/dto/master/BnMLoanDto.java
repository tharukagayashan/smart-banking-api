package com.projects.smartbankingapi.dto.master;

import com.projects.smartbankingapi.dto.reference.BnRLoanProductDto;
import com.projects.smartbankingapi.dto.reference.BnRStatusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.master.BnMLoan}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnMLoanDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long loanId;
    private Float amount;
    private Float interest;
    private Integer totInstallments;
    private LocalDate nextInstallmentDate;
    private Float totArrearsAmt;
    private Integer remInstallments;
    private Float nextInstallmentAmt;
    private Float distributedAmt;
    private Float totSettledAmt;
    private Float totInterestPaid;
    private Float totPaid;

    private Long accountId;
    private Long statusId;
    private Long productId;

    private BnRStatusDto bnRStatus;
    private BnMAccountDto bnMAccount;
    private BnRLoanProductDto bnRLoanProduct;
}