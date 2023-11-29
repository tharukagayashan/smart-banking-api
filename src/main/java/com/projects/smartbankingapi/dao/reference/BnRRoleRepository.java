package com.projects.smartbankingapi.dao.reference;

import com.projects.smartbankingapi.model.reference.BnRRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BnRRoleRepository extends JpaRepository<BnRRole, Long> {
    Optional<BnRRole> findByRoleName(String roleName);
}