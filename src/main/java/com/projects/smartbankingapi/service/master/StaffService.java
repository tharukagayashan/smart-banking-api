package com.projects.smartbankingapi.service.master;

import com.projects.smartbankingapi.dto.master.BnMStaffDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StaffService {
    ResponseEntity<List<BnMStaffDto>> getAllStaffs();

    ResponseEntity<ApiResponseDto<List<BnMStaffDto>>> getStaffForTable(Integer page, Integer perPage, String sort, String direction, String search);

    ResponseEntity<BnMStaffDto> getStaff(Long staffId);
}
