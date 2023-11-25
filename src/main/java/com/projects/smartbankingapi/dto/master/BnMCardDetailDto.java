package com.projects.smartbankingapi.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.master.BnMCardDetail}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnMCardDetailDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long cardId;
    private String cardType;
    private String cardNo;
    private String expiryDate;
    private String cvv;
}