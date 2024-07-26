package com.cosmo.authentication.emailtemplate.repo;

import com.cosmo.authentication.emailtemplate.entity.AdminEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminEmailLogRepository extends JpaRepository<AdminEmailLog,Long> {
    Optional<AdminEmailLog> findByUuid(String uuid);
}
