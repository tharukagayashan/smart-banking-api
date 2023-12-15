package com.projects.smartbankingapi.dao.master;

import com.projects.smartbankingapi.model.master.BnMAccountCardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BnMAccountCardDetailRepository extends JpaRepository<BnMAccountCardDetail, Long> {

    Optional<BnMAccountCardDetail> findByBnMAccountAccountId(Long accountId);

}