package com.projects.smartbankingapi.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDetailCreateReqDto {
    @NotNull
    @NotEmpty
    @NotBlank
    private String cardType;
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 16, max = 16)
    private String cardNo;
    @NotNull
    @NotEmpty
    @NotBlank
    private String expiryDate;
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 3, max = 3)
    private String cvv;
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 4, max = 4)
    private String pin;
}