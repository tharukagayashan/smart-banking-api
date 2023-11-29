package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRLoanType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BnRLoanTypeRepository extends JpaRepository<BnRLoanType, Long> {
    Optional<BnRLoanType> findByCode(String code);
}