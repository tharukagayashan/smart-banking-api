package com.projects.smartbankingapi.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private int httpCode;
    private String errorCode;
    private String description;
}
