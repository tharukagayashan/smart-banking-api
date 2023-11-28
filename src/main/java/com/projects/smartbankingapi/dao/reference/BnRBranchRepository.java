package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BnRBranchRepository extends JpaRepository<BnRBranch, Long> {
    List<BnRBranch> findAllByBnRBankBankId(Long bankId);
}