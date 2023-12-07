package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TranCreateReqDto {
    @Max(1000000)
    @Min(0)
    private Float amount;
    private String description;
    @NotNull
    @NotEmpty
    @NotBlank
    private String fromAccountNo;
    @NotNull
    @NotEmpty
    @NotBlank
    private String toAccountNo;
    @NotNull
    private Long tranTypeId;
    private Long statusId;
    private Long branchId;
}