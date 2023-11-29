package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BnRBankRepository extends JpaRepository<BnRBank, Long> {
    Optional<BnRBank> findByCode(String code);
}