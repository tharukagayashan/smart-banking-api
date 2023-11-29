package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRAccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BnRAccountTypeRepository extends JpaRepository<BnRAccountType, Long> {
    Optional<BnRAccountType> findByCode(String code);
}