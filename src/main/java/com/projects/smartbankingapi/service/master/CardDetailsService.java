package com.projects.smartbankingapi.service.master;

import com.projects.smartbankingapi.dto.master.BnMAccountCardDetailDto;
import com.projects.smartbankingapi.dto.master.BnMCardDetailDto;
import com.projects.smartbankingapi.dto.other.CardDetailCreateReqDto;
import com.projects.smartbankingapi.dto.other.CardLinkReqDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CardDetailsService {
    ResponseEntity<BnMCardDetailDto> createCardDetails(CardDetailCreateReqDto cardDetailCreateReqDto);

    ResponseEntity<BnMCardDetailDto> getCardDetails(Long cardId);

    ResponseEntity<BnMCardDetailDto> updateCardDetails(Long cardId, BnMCardDetailDto bnMCardDetailDto);

    ResponseEntity<String> deleteCardDetails(Long cardId);

    ResponseEntity<List<BnMCardDetailDto>> getAllCardDetails(String cardType);

    ResponseEntity<BnMAccountCardDetailDto> linkCardToAccount(CardLinkReqDto cardLinkReqDto);
}
