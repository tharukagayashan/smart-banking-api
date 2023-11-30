package com.projects.smartbankingapi.service.other.impl;

import com.projects.smartbankingapi.config.JWTUtils;
import com.projects.smartbankingapi.config.PasswordUtils;
import com.projects.smartbankingapi.dao.master.BnMStaffRepository;
import com.projects.smartbankingapi.dao.reference.BnRBranchRepository;
import com.projects.smartbankingapi.dao.reference.BnRRoleRepository;
import com.projects.smartbankingapi.dto.auth.LoginReqDto;
import com.projects.smartbankingapi.dto.auth.LoginResponseDto;
import com.projects.smartbankingapi.dto.auth.StaffRegisterReqDto;
import com.projects.smartbankingapi.dto.auth.TokenDto;
import com.projects.smartbankingapi.dto.master.BnMStaffDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.master.BnMStaffMapper;
import com.projects.smartbankingapi.model.master.BnMStaff;
import com.projects.smartbankingapi.model.reference.BnRBranch;
import com.projects.smartbankingapi.model.reference.BnRRole;
import com.projects.smartbankingapi.service.other.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final BnMStaffRepository staffRepository;
    private final BnMStaffMapper staffMapper;
    private final BnRRoleRepository roleRepository;
    private final BnRBranchRepository branchRepository;

    public AuthServiceImpl(BnMStaffRepository staffRepository, BnMStaffMapper staffMapper, BnRRoleRepository roleRepository, BnRBranchRepository branchRepository) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
        this.roleRepository = roleRepository;
        this.branchRepository = branchRepository;
    }


    @Override
    public ResponseEntity<BnMStaffDto> registerStaffMember(StaffRegisterReqDto staffRegisterReqDto) {
        try {

            Optional<BnMStaff> optStaff = staffRepository.findByUsername(staffRegisterReqDto.getUsername());
            if (optStaff.isPresent()) {
                throw new BadRequestAlertException("Username already exists", "staff", "staff");
            } else {
                Optional<BnRRole> optRole = roleRepository.findById(staffRegisterReqDto.getRoleId());
                Optional<BnRBranch> optBranch = branchRepository.findById(staffRegisterReqDto.getBranchId());

                if (!optRole.isPresent()) {
                    throw new BadRequestAlertException("Role not found", "staff", "staff");
                } else if (!optBranch.isPresent()) {
                    throw new BadRequestAlertException("Branch not found", "staff", "staff");
                } else {
                    log.info("Role and Branch found");
                }

                String encryptedPassword = PasswordUtils.encodePassword(staffRegisterReqDto.getPassword());

                BnMStaff staff = new BnMStaff();
                staff.setFirstName(staffRegisterReqDto.getFirstName());
                staff.setLastName(staffRegisterReqDto.getLastName());
                staff.setEmail(staffRegisterReqDto.getEmail());
                staff.setUsername(staffRegisterReqDto.getUsername());
                staff.setPassword(encryptedPassword);
                staff.setMobileNo(staffRegisterReqDto.getMobileNo());
                staff.setAddress(staffRegisterReqDto.getAddress());
                staff.setIsActive(true);
                staff.setBnRRole(optRole.get());
                staff.setBnRBranch(optBranch.get());
                staff = staffRepository.save(staff);
                if (staff.getStaffId() == null) {
                    throw new BadRequestAlertException("Error while registering staff member", "staff", "staff");
                } else {
                    log.info("Staff member registered successfully");
                    return ResponseEntity.ok(staffMapper.toDto(staff));
                }
            }
        } catch (Exception e) {
            log.error("Error while registering staff member: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "staff", "staff");
        }
    }

    @Override
    public ResponseEntity<LoginResponseDto> loginStaffMember(LoginReqDto loginReqDto) {
        try {

            Optional<BnMStaff> optStaff = staffRepository.findByUsername(loginReqDto.getUsername());
            if (!optStaff.isPresent()) {
                throw new BadRequestAlertException("Invalid username or password", "staff", "staff");
            } else {
                BnMStaff staff = optStaff.get();
                if (!PasswordUtils.isPasswordValid(loginReqDto.getPassword(), staff.getPassword())) {
                    throw new BadRequestAlertException("Invalid username or password", "staff", "staff");
                } else {
                    if (!staff.getIsActive()) {
                        throw new BadRequestAlertException("Staff member is not active", "staff", "staff");
                    } else {

                        try {
                            TokenDto tokenDto = new TokenDto();
                            tokenDto.setUsername(staff.getUsername());
                            tokenDto.setFirstName(staff.getFirstName());
                            tokenDto.setLastName(staff.getLastName());
                            tokenDto.setRole(staff.getBnRRole().getRoleName());
                            tokenDto.setEmail(staff.getEmail());
                            tokenDto.setBank(staff.getBnRBranch().getBnRBank().getName());
                            tokenDto.setBranch(staff.getBnRBranch().getName());
                            tokenDto.setBankCode(staff.getBnRBranch().getBnRBank().getCode());
                            tokenDto.setBranchCode(staff.getBnRBranch().getCode());
                            tokenDto.setRoleId(staff.getBnRRole().getRoleId());

                            String generatedToken = JWTUtils.generateJWTToken(tokenDto);

                            LoginResponseDto loginResponseDto = new LoginResponseDto();
                            loginResponseDto.setToken(generatedToken);
                            loginResponseDto.setDetails(tokenDto);

                            return ResponseEntity.ok(loginResponseDto);
                        } catch (Exception e) {
                            log.error("Error while generating token: {}", e.getMessage());
                            throw new BadRequestAlertException(e.getMessage(), "staff", "staff");
                        }
                    }
                }
            }

        } catch (Exception e) {
            log.error("Error while logging in staff member: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "staff", "staff");
        }
    }

    @Override
    public ResponseEntity<BnMStaffDto> getLoginUserDetails(String token) {
        try {
            if (!JWTUtils.validateJWTToken(token)) {
                throw new BadRequestAlertException("Invalid token", "staff", "staff");
            } else {
                String username = JWTUtils.getUsernameFromToken(token);
                Optional<BnMStaff> optStaff = staffRepository.findByUsername(username);
                if (!optStaff.isPresent()) {
                    throw new BadRequestAlertException("Invalid token", "staff", "staff");
                } else {
                    BnMStaff staff = optStaff.get();
                    if (!staff.getIsActive()) {
                        throw new BadRequestAlertException("Staff member is not active", "staff", "staff");
                    } else {
                        return ResponseEntity.ok(staffMapper.toDto(staff));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while getting login user details: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "staff", "staff");
        }
    }
}
