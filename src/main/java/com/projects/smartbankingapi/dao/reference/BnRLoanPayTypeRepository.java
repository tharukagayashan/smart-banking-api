package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRLoanPayType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BnRLoanPayTypeRepository extends JpaRepository<BnRLoanPayType, Long> {
    Optional<BnRLoanPayType> findByPayTypeCode(String payTypeCode);
}
