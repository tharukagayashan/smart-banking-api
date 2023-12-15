package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMCardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BnMCardDetailRepository extends JpaRepository<BnMCardDetail, Long> {
    Optional<BnMCardDetail> findByCardNo(String cardNo);

    List<BnMCardDetail> findAllByCardType(String cardType);
}