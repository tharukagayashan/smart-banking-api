package com.projects.smartbankingapi.service.master.impl;

import com.projects.smartbankingapi.constant.HardCodeConstant;
import com.projects.smartbankingapi.dao.master.BnMAccountCardDetailRepository;
import com.projects.smartbankingapi.dao.master.BnMAccountRepository;
import com.projects.smartbankingapi.dao.master.BnMCardDetailRepository;
import com.projects.smartbankingapi.dto.master.BnMAccountCardDetailDto;
import com.projects.smartbankingapi.dto.master.BnMCardDetailDto;
import com.projects.smartbankingapi.dto.other.CardDetailCreateReqDto;
import com.projects.smartbankingapi.dto.other.CardLinkReqDto;
import com.projects.smartbankingapi.error.BadRequestAlertException;
import com.projects.smartbankingapi.mapper.master.BnMAccountCardDetailMapper;
import com.projects.smartbankingapi.mapper.master.BnMCardDetailMapper;
import com.projects.smartbankingapi.model.master.BnMAccount;
import com.projects.smartbankingapi.model.master.BnMAccountCardDetail;
import com.projects.smartbankingapi.model.master.BnMCardDetail;
import com.projects.smartbankingapi.service.master.CardDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CardDetailsServiceImpl implements CardDetailsService {

    private final BnMCardDetailMapper cardDetailMapper;
    private final BnMCardDetailRepository cardDetailRepository;
    private final BnMAccountCardDetailRepository accountCardDetailRepository;
    private final BnMAccountRepository accountRepository;
    private final BnMAccountCardDetailMapper accountCardDetailMapper;

    public CardDetailsServiceImpl(BnMCardDetailMapper cardDetailMapper, BnMCardDetailRepository cardDetailRepository, BnMAccountCardDetailRepository accountCardDetailRepository, BnMAccountRepository accountRepository, BnMAccountCardDetailMapper accountCardDetailMapper) {
        this.cardDetailMapper = cardDetailMapper;
        this.cardDetailRepository = cardDetailRepository;
        this.accountCardDetailRepository = accountCardDetailRepository;
        this.accountRepository = accountRepository;
        this.accountCardDetailMapper = accountCardDetailMapper;
    }

    @Override
    public ResponseEntity<BnMCardDetailDto> createCardDetails(CardDetailCreateReqDto cardDetailCreateReqDto) {
        try {

            Optional<BnMCardDetail> optCard = cardDetailRepository.findByCardNo(cardDetailCreateReqDto.getCardNo());
            if (optCard.isPresent()) {
                throw new BadRequestAlertException("Card already exists", "card-details", "card-already-exists");
            } else {
                String cardType;
                if (cardDetailCreateReqDto.getCardType().equalsIgnoreCase(HardCodeConstant.CARD_TYPE_MASTER)) {
                    cardType = HardCodeConstant.CARD_TYPE_MASTER;
                } else if (cardDetailCreateReqDto.getCardType().equalsIgnoreCase(HardCodeConstant.CARD_TYPE_VISA)) {
                    cardType = HardCodeConstant.CARD_TYPE_VISA;
                } else {
                    throw new BadRequestAlertException("Invalid card type", "card-details", "invalid-card-type");
                }

                BnMCardDetail cardDetail = new BnMCardDetail();
                cardDetail.setCardType(cardType);
                cardDetail.setCardNo(cardDetailCreateReqDto.getCardNo());
                cardDetail.setExpiryDate(cardDetailCreateReqDto.getExpiryDate());
                cardDetail.setCvv(cardDetailCreateReqDto.getCvv());
                cardDetail.setPin(cardDetailCreateReqDto.getPin());

                cardDetail = cardDetailRepository.save(cardDetail);
                if (cardDetail.getCardId() != null) {
                    return ResponseEntity.ok(cardDetailMapper.toDto(cardDetail));
                } else {
                    throw new BadRequestAlertException("Error creating card details", "card-details", "error-creating-card-details");
                }
            }
        } catch (Exception e) {
            log.error("Error creating card details: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "card-details", "error-creating-card-details");
        }
    }

    @Override
    public ResponseEntity<BnMCardDetailDto> getCardDetails(Long cardId) {
        try {
            if (cardId == null) {
                throw new BadRequestAlertException("Invalid card id", "card-details", "invalid-card-id");
            } else {
                Optional<BnMCardDetail> optCard = cardDetailRepository.findById(cardId);
                if (!optCard.isPresent()) {
                    throw new BadRequestAlertException("Card not found", "card-details", "card-not-found");
                } else {
                    return ResponseEntity.ok(cardDetailMapper.toDto(optCard.get()));
                }
            }
        } catch (Exception e) {
            log.error("Error getting card details: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "card-details", "error-getting-card-details");
        }
    }

    @Override
    public ResponseEntity<BnMCardDetailDto> updateCardDetails(Long cardId, BnMCardDetailDto bnMCardDetailDto) {
        try {
            if (cardId == null) {
                throw new BadRequestAlertException("Invalid card id", "card-details", "invalid-card-id");
            } else if (cardId != bnMCardDetailDto.getCardId()) {
                throw new BadRequestAlertException("cardId mismatched", "card-details", "invalid-card-id");
            } else {
                Optional<BnMCardDetail> optCard = cardDetailRepository.findById(cardId);
                if (!optCard.isPresent()) {
                    throw new BadRequestAlertException("Card not found", "card-details", "card-not-found");
                } else {
                    BnMCardDetail cardDetail = optCard.get();
                    cardDetail.setCardType(bnMCardDetailDto.getCardType());
                    cardDetail.setCardNo(bnMCardDetailDto.getCardNo());
                    cardDetail.setExpiryDate(bnMCardDetailDto.getExpiryDate());
                    cardDetail.setCvv(bnMCardDetailDto.getCvv());
                    cardDetail.setPin(bnMCardDetailDto.getPin());

                    cardDetail = cardDetailRepository.save(cardDetail);
                    return ResponseEntity.ok(cardDetailMapper.toDto(cardDetail));
                }
            }
        } catch (Exception e) {
            log.error("Error updating card details: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "card-details", "error-updating-card-details");
        }
    }

    @Override
    public ResponseEntity<String> deleteCardDetails(Long cardId) {
        try {
            if (cardId == null) {
                throw new BadRequestAlertException("Invalid card id", "card-details", "invalid-card-id");
            } else {
                Optional<BnMCardDetail> optCard = cardDetailRepository.findById(cardId);
                if (!optCard.isPresent()) {
                    throw new BadRequestAlertException("Card not found", "card-details", "card-not-found");
                } else {
                    cardDetailRepository.deleteById(cardId);
                    Optional<BnMCardDetail> checkCard = cardDetailRepository.findById(cardId);
                    if (checkCard.isPresent()) {
                        throw new BadRequestAlertException("Error deleting card details", "card-details", "error-deleting-card-details");
                    } else {
                        return ResponseEntity.ok("Card details deleted successfully");
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error deleting card details: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "card-details", "error-deleting-card-details");
        }
    }

    @Override
    public ResponseEntity<List<BnMCardDetailDto>> getAllCardDetails(String cardType) {
        try {
            if (cardType == null) {
                throw new BadRequestAlertException("card type null", "card-details", "invalid-card-type");
            } else {
                List<BnMAccountCardDetail> accountCards = accountCardDetailRepository.findAll();
                List<BnMCardDetail> cards = cardDetailRepository.findAllByCardType(cardType);

                if (cards.isEmpty()) {
                    throw new BadRequestAlertException("No card details found", "card-details", "no-card-details-found");
                } else {
                    for (BnMCardDetail c : cards) {
                        for (BnMAccountCardDetail ac : accountCards) {
                            if (c.getCardId().equals(ac.getBnMCardDetail().getCardId())) {
                                cards.remove(c);
                            }
                        }
                    }
                    return ResponseEntity.ok(cardDetailMapper.entityListToDtoList(cards));
                }
            }
        } catch (Exception e) {
            log.error("Error getting card details: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "card-details", "error-getting-card-details");
        }
    }

    @Override
    public ResponseEntity<BnMAccountCardDetailDto> linkCardToAccount(CardLinkReqDto cardLinkReqDto) {
        try {
            Optional<BnMCardDetail> optCard = cardDetailRepository.findById(cardLinkReqDto.getCardId());
            Optional<BnMAccount> optAccount = accountRepository.findById(cardLinkReqDto.getAccountId());

            if (!optCard.isPresent()) {
                throw new BadRequestAlertException("Card not found", "card-details", "card-not-found");
            }
            if (!optAccount.isPresent()) {
                throw new BadRequestAlertException("Account not found", "card-details", "account-not-found");
            } else {

                Optional<BnMAccountCardDetail> optAccountCard = accountCardDetailRepository.findByBnMAccountAccountId(optAccount.get().getAccountId());
                if (optAccountCard.isPresent()) {
                    throw new BadRequestAlertException("Account already linked to a card", "card-details", "account-already-linked-to-card");
                }

                BnMAccountCardDetail accountCardDetail = new BnMAccountCardDetail();
                accountCardDetail.setBnMAccount(optAccount.get());
                accountCardDetail.setBnMCardDetail(optCard.get());
                accountCardDetail = accountCardDetailRepository.save(accountCardDetail);
                if (accountCardDetail.getAccCardId() == null) {
                    throw new BadRequestAlertException("Error linking card to account", "card-details", "error-linking-card-to-account");
                } else {
                    log.info("Card linked to account successfully");
                    return ResponseEntity.ok(accountCardDetailMapper.toDto(accountCardDetail));
                }

            }
        } catch (Exception e) {
            log.error("Error linking card to account: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "card-details", "error-linking-card-to-account");
        }
    }
}
