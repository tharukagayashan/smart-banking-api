package com.projects.smartbankingapi.rest.master;

import com.projects.smartbankingapi.dto.master.BnMAccountCardDetailDto;
import com.projects.smartbankingapi.dto.master.BnMCardDetailDto;
import com.projects.smartbankingapi.dto.other.CardDetailCreateReqDto;
import com.projects.smartbankingapi.dto.other.CardLinkReqDto;
import com.projects.smartbankingapi.service.master.CardDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Slf4j
@RestController
@RequestMapping("/card-details")
public class CardDetailsController {

    private final CardDetailsService cardDetailsService;

    public CardDetailsController(CardDetailsService cardDetailsService) {
        this.cardDetailsService = cardDetailsService;
    }

    @PostMapping
    public ResponseEntity<BnMCardDetailDto> createCardDetails(@Valid @RequestBody CardDetailCreateReqDto cardDetailCreateReqDto) {
        return cardDetailsService.createCardDetails(cardDetailCreateReqDto);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<BnMCardDetailDto> getCardDetails(@PathVariable Long cardId) {
        return cardDetailsService.getCardDetails(cardId);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<BnMCardDetailDto> updateCardDetails(@PathVariable Long cardId, @Valid @RequestBody BnMCardDetailDto bnMCardDetailDto) {
        return cardDetailsService.updateCardDetails(cardId, bnMCardDetailDto);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> deleteCardDetails(@PathVariable Long cardId) {
        return cardDetailsService.deleteCardDetails(cardId);
    }

    @PostMapping("/link-to-account")
    public ResponseEntity<BnMAccountCardDetailDto> linkCardToAccount(@RequestBody CardLinkReqDto cardLinkReqDto) {
        return cardDetailsService.linkCardToAccount(cardLinkReqDto);
    }

    @GetMapping("/all/cardType/{cardType}/unlinked")
    public ResponseEntity<List<BnMCardDetailDto>> getAllCardDetails(@PathVariable String cardType) {
        return cardDetailsService.getAllCardDetails(cardType);
    }

}