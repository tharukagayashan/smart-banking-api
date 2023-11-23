package com.projects.smartbankingapi.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponseDto extends Throwable {
    private String message;
    private Integer statusCode;
}