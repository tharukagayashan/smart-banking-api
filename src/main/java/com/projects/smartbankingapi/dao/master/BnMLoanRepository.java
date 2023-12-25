package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMLoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BnMLoanRepository extends JpaRepository<BnMLoan, Long> {

    @Query("SELECT l FROM BnMLoan l WHERE l.loanId = ?1 OR l.bnRStatus.name LIKE ?1")
    Page<BnMLoan> getLoanForTable(String search, PageRequest of);

    List<BnMLoan> findByBnRStatusStatusId(Long statusId);
}