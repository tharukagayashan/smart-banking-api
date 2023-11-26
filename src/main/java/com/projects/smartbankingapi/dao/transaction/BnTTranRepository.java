package com.projects.smartbankingapi.dao.transaction;

import com.projects.smartbankingapi.model.transaction.BnTTran;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BnTTranRepository extends JpaRepository<BnTTran, Long> {

    @Query("SELECT t FROM BnTTran t " +
            "WHERE (:fromAccountNo IS NULL OR t.fromAccountNo LIKE %:fromAccountNo%) " +
            "AND (:toAccountNo IS NULL OR t.toAccountNo LIKE %:toAccountNo%) " +
            "AND (:search IS NULL OR t.description LIKE %:search%)"
    )
    Page<BnTTran> findByFilter(String search, String fromAccountNo, String toAccountNo, PageRequest of);

}