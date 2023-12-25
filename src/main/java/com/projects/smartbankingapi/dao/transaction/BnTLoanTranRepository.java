package com.projects.smartbankingapi.dao.transaction;

import com.projects.smartbankingapi.model.transaction.BnTLoanTran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BnTLoanTranRepository extends JpaRepository<BnTLoanTran, Long> {
}
