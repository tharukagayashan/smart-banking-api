package com.projects.smartbankingapi.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.projects.smartbankingapi.model.reference.BnRStatus}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BnRStatusDto implements Serializable {
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Long statusId;
    private String name;
    private String code;
    private String type;
}