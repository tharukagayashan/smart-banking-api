package com.projects.smartbankingapi.global;

import com.projects.smartbankingapi.error.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalErrorHandler {

    public ErrorResponseDto handleException(String e) throws ErrorResponseDto {
        throw new ErrorResponseDto(e, 400);
    }

}