package com.projects.smartbankingapi.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestAlertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String message;
    private final String entityName;
    private final String errorKey;

    public BadRequestAlertException(String message, String entityName, String errorKey) {
        this.message = message;
        this.entityName = entityName;
        this.errorKey = errorKey;
    }
}