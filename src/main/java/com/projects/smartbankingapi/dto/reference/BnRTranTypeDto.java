package com.projects.smartbankingapi.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.reference.BnRTranType}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnRTranTypeDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long tranTypeId;
    private String name;
    private String code;
}