package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRLoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BnRLoanProductRepository extends JpaRepository<BnRLoanProduct, Long> {
}