package com.projects.smartbankingapi.rest.master;

import com.projects.smartbankingapi.dto.master.BnMStaffDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.service.master.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BnMStaffDto>> getAllStaffs() {
        ResponseEntity<List<BnMStaffDto>> response = staffService.getAllStaffs();
        return response;
    }

    @GetMapping("/table")
    public ResponseEntity<ApiResponseDto<List<BnMStaffDto>>> getStaffForTable(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer perPage,
            @RequestParam(defaultValue = "staffId", required = false) String sort,
            @RequestParam(defaultValue = "desc", required = false) String direction,
            @RequestParam(defaultValue = "", required = false) String search
    ) {
        ResponseEntity<ApiResponseDto<List<BnMStaffDto>>> response = staffService.getStaffForTable(page, perPage, sort, direction, search);
        return response;
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<BnMStaffDto> getStaff(@PathVariable Long staffId) {
        ResponseEntity<BnMStaffDto> response = staffService.getStaff(staffId);
        return response;
    }

}
