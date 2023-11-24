package com.projects.smartbankingapi.global;

import com.projects.smartbankingapi.error.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        String message = error.getField()+ " : " +error.getDefaultMessage();
        log.debug("Validation error: {}", message);
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setHttpCode(400);
        errorResponse.setErrorCode("VALIDATION FAILED!");
        errorResponse.setDescription(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

