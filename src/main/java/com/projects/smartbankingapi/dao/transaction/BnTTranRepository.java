package com.projects.smartbankingapi.dao.transaction;

import com.projects.smartbankingapi.model.transaction.BnTTran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BnTTranRepository extends JpaRepository<BnTTran, Long> {
}