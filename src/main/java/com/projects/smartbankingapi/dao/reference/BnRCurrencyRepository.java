package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BnRCurrencyRepository extends JpaRepository<BnRCurrency, Long> {
}