package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRCurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BnRCurrencyRateRepository extends JpaRepository<BnRCurrencyRate, Long> {

    @Query("SELECT c FROM BnRCurrencyRate c WHERE c.bnRCurrency.currencyId = ?1 ORDER BY c.publicationDate DESC")
    List<BnRCurrencyRate> findLatestCurrencyRateRecord(Long currencyId);

}
