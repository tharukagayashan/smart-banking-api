package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BnMAccountRepository extends JpaRepository<BnMAccount, Long> {
}