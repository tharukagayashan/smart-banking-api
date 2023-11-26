package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TranCreateReqDto {
    @NotNull
    @Max(1000000)
    @Min(0)
    private Float amount;
    @NotNull
    private Long tranTypeId;
    @NotNull
    private Long accountId;
}