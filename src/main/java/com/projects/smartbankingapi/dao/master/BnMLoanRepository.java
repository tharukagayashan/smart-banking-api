package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BnMLoanRepository extends JpaRepository<BnMLoan, Long> {
}