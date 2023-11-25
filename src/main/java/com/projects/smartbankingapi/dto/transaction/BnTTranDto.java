package com.projects.smartbankingapi.dto.transaction;

import com.projects.smartbankingapi.dto.master.BnMAccountDto;
import com.projects.smartbankingapi.dto.reference.BnRTranTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.transaction.BnTTran}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnTTranDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long tranId;
    private Float amount;
    private LocalDate tranDate;
    private LocalTime tranTime;
    private BnRTranTypeDto bnRTranType;
    private BnMAccountDto bnMAccount;
}