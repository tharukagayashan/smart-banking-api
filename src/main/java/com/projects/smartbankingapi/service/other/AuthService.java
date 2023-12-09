package com.projects.smartbankingapi.service.other;

import com.projects.smartbankingapi.dto.auth.LoginReqDto;
import com.projects.smartbankingapi.dto.auth.LoginResponseDto;
import com.projects.smartbankingapi.dto.auth.StaffRegisterReqDto;
import com.projects.smartbankingapi.dto.auth.TokenDto;
import com.projects.smartbankingapi.dto.master.BnMStaffDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<BnMStaffDto> registerStaffMember(StaffRegisterReqDto staffRegisterReqDto);

    ResponseEntity<LoginResponseDto> loginStaffMember(LoginReqDto loginReqDto);

    ResponseEntity<BnMStaffDto> getLoginUserDetails(String token);

    ResponseEntity<TokenDto> getLoginUserByToken(String token);
}
