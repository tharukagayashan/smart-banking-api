package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BnMCustomerRepository extends JpaRepository<BnMCustomer, Long> {
}