package com.projects.smartbankingapi.service.master.impl;

import com.projects.smartbankingapi.dao.master.BnMStaffRepository;
import com.projects.smartbankingapi.dto.master.BnMStaffDto;
import com.projects.smartbankingapi.dto.miscellaneous.ApiResponseDto;
import com.projects.smartbankingapi.dto.miscellaneous.PaginationDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.master.BnMStaffMapper;
import com.projects.smartbankingapi.model.master.BnMStaff;
import com.projects.smartbankingapi.service.master.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StaffServiceImpl implements StaffService {

    private final BnMStaffRepository staffRepository;
    private final BnMStaffMapper staffMapper;

    public StaffServiceImpl(BnMStaffRepository staffRepository, BnMStaffMapper staffMapper) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    public ResponseEntity<List<BnMStaffDto>> getAllStaffs() {
        try {
            List<BnMStaff> staffs = staffRepository.findAll();
            if (staffs.isEmpty()) {
                throw new BadRequestAlertException("No staffs found", "staff", "not.found");
            } else {
                List<BnMStaffDto> staffDtos = staffMapper.entityListToDtoList(staffs);
                return ResponseEntity.ok(staffDtos);
            }
        } catch (Exception e) {
            log.error("Error occurred while getting all staffs", e);
            throw new BadRequestAlertException(e.getMessage(), "staff", "error");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<BnMStaffDto>>> getStaffForTable(Integer page, Integer perPage, String sort, String direction, String search) {
        try {
            Page<BnMStaff> dbData = null;
            if (direction.equalsIgnoreCase("asc")) {
                dbData = staffRepository.getStaffForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort)));
            } else {
                dbData = staffRepository.getStaffForTable(search, PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort)));
            }

            ApiResponseDto<List<BnMStaffDto>> response = new ApiResponseDto<>();
            PaginationDto pagination = new PaginationDto();
            pagination.setTotal(dbData.getTotalElements());
            pagination.setPerPage(dbData.getSize());
            pagination.setCurrentPage(dbData.getNumber());
            pagination.setFrom(perPage * page + 1);
            pagination.setTo(perPage * page + dbData.getNumberOfElements());
            response.setPagination(pagination);
            response.setResult(staffMapper.entityListToDtoList(dbData.getContent()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while getting staffs for table", e);
            throw new BadRequestAlertException(e.getMessage(), "staff", "error");
        }
    }

    @Override
    public ResponseEntity<BnMStaffDto> getStaff(Long staffId) {
        try {
            if (staffId == null) {
                throw new BadRequestAlertException("Staff id is required", "staff", "required");
            } else {
                Optional<BnMStaff> optStaff = staffRepository.findById(staffId);
                if (!optStaff.isPresent()) {
                    throw new BadRequestAlertException("Staff not found", "staff", "not.found");
                } else {
                    BnMStaffDto staffDto = staffMapper.toDto(optStaff.get());
                    return ResponseEntity.ok(staffDto);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while getting staff", e);
            throw new BadRequestAlertException(e.getMessage(), "staff", "error");
        }
    }

}
