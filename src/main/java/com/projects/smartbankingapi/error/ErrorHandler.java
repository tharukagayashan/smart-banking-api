package com.projects.smartbankingapi.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The type Error handler.
 *
 * @author : Steffan Devotta
 * @version : 1.0
 * @date : Sep 30, 2021
 * @copyright : © 2010-2021 Informatics International Limited. All Rights Reserved
 */
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle bad request alert exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(BadRequestAlertException.class)
    public ResponseEntity<Object> handleBadRequestAlertException(BadRequestAlertException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("error", ex.getErrorKey());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorizationAlertException.class)
    public ResponseEntity<Object> handleAuthorizationAlertException(AuthorizationAlertException ae, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", ae.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

}
