package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BnMCustomerRepository extends JpaRepository<BnMCustomer, Long> {

    Optional<BnMCustomer> findByNic(String nic);

    List<BnMCustomer> findAllByIsActive(Boolean isActive);

    @Query("SELECT c FROM BnMCustomer c WHERE c.firstName LIKE %?1% OR c.lastName LIKE %?1% OR c.nic LIKE %?1% OR c.mobileNo LIKE %?1% OR c.email LIKE %?1%")
    Page<BnMCustomer> findAllForTable(String search, PageRequest of);
}