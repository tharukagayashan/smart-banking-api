package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BnRCurrencyRepository extends JpaRepository<BnRCurrency, Long> {
    Optional<BnRCurrency> findByCode(String code);
}