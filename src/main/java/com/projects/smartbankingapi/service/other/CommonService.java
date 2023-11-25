package com.projects.smartbankingapi.service.other;

import com.projects.smartbankingapi.dto.other.ValidateNICReqDto;
import com.projects.smartbankingapi.dto.other.ValidateNICResDto;
import org.springframework.http.ResponseEntity;

public interface CommonService {
    ResponseEntity<ValidateNICResDto> validateNic(ValidateNICReqDto validateNICReqDto);
}
