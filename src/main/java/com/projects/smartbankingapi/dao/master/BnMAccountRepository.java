package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BnMAccountRepository extends JpaRepository<BnMAccount, Long> {

    Optional<BnMAccount> findByBnMCustomerNic(String nic);

    List<BnMAccount> findAllByIsActive(Boolean isActive);

    @Query("SELECT a FROM BnMAccount a WHERE a.isActive = true AND (a.accountNo LIKE %?1% OR a.bnMCustomer.firstName LIKE %?1% OR a.bnMCustomer.lastName LIKE %?1% OR a.bnMCustomer.nic LIKE %?1% OR a.bnMCustomer.mobileNo LIKE %?1% OR a.bnMCustomer.email LIKE %?1%)")
    Page<BnMAccount> findAllForTable(String search, PageRequest of);
}