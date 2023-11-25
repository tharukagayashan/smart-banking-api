package com.projects.smartbankingapi.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.master.BnMAccountCardDetail}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnMAccountCardDetailDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long accCardId;
    private BnMAccountDto bnMAccount;
    private BnMCardDetailDto bnMCardDetail;
}