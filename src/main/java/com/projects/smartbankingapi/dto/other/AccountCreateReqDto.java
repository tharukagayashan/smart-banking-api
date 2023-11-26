package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCreateReqDto {
    @NotNull
    private Long customerId;
    @NotNull
    private Long accountTypeId;
    @NotNull
    private Long currencyId;
    @NotNull
    private Long branchId;
}
