package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BnRStatusRepository extends JpaRepository<BnRStatus, Long> {
}