package com.projects.smartbankingapi.dto.miscellaneous;

import java.io.Serializable;

public class ApiResponseDto<T> implements Serializable {
    private PaginationDto pagination;
    private T result;

    public PaginationDto getPagination() {
        return this.pagination;
    }

    public void setPagination(PaginationDto pagination) {
        this.pagination = pagination;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}