package com.projects.smartbankingapi.dao.transaction;

import com.projects.smartbankingapi.model.transaction.BnTTran;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface BnTTranRepository extends JpaRepository<BnTTran, Long> {

    @Query("SELECT t FROM BnTTran t " +
            "WHERE (:fromAccountNo IS '' OR t.fromAccountNo LIKE %:fromAccountNo%) " +
            "AND (:toAccountNo IS '' OR t.toAccountNo LIKE %:toAccountNo%) " +
            "AND (:search IS '' OR t.description LIKE %:search%) " +
            "AND (:fromDate IS '' OR t.tranDate >= :fromDate) " +
            "AND (:toDate IS '' OR t.tranDate <= :toDate)"
    )
    Page<BnTTran> findByFilter(String search, String fromAccountNo, String toAccountNo, LocalDate fromDate, LocalDate toDate, PageRequest of);

}