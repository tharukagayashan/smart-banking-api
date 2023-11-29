package com.projects.smartbankingapi.rest.other;

import com.projects.smartbankingapi.dto.auth.LoginReqDto;
import com.projects.smartbankingapi.dto.auth.LoginResponseDto;
import com.projects.smartbankingapi.dto.auth.StaffRegisterReqDto;
import com.projects.smartbankingapi.dto.master.BnMStaffDto;
import com.projects.smartbankingapi.service.other.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<BnMStaffDto> registerStaffMember(@RequestBody StaffRegisterReqDto staffRegisterReqDto) {
        ResponseEntity<BnMStaffDto> response = authService.registerStaffMember(staffRegisterReqDto);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginStaffMember(@RequestBody LoginReqDto loginReqDto) {
        ResponseEntity<LoginResponseDto> response = authService.loginStaffMember(loginReqDto);
        return response;
    }

    @GetMapping("/login-user-details")
    public ResponseEntity<BnMStaffDto> getLoginUserDetails(@RequestHeader("Authorization") String token ) {
        ResponseEntity<BnMStaffDto> response = authService.getLoginUserDetails(token);
        return response;
    }

}
