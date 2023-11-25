package com.projects.smartbankingapi.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.reference.BnRLoanPeriod}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnRLoanPeriodDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long periodId;
    private String name;
    private String description;
    private Integer month;
}