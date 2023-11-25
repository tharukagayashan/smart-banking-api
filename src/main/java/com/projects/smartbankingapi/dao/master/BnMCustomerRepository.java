package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BnMCustomerRepository extends JpaRepository<BnMCustomer, Long> {

    Optional<BnMCustomer> findByNic(String nic);

}