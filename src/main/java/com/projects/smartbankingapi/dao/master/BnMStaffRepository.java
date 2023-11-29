package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BnMStaffRepository extends JpaRepository<BnMStaff, Long> {

    @Query("SELECT s FROM BnMStaff s WHERE s.firstName LIKE %?1% OR s.lastName LIKE %?1% OR s.email LIKE %?1% OR s.mobileNo LIKE %?1% OR s.address LIKE %?1%")
    Page<BnMStaff> getStaffForTable(String search, PageRequest of);

    Optional<BnMStaff> findByUsername(String username);
}