package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRTranType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BnRTranTypeRepository extends JpaRepository<BnRTranType, Long> {
    Optional<BnRTranType> findByCode(String code);
}