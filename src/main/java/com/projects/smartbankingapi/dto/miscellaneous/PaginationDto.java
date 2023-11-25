package com.projects.smartbankingapi.dto.miscellaneous;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginationDto {
    private Integer from;
    private Integer to;
    private Integer perPage;
    private Integer currentPage;
    private Long total;
}