package com.cosmo.authentication.log.repo;

import com.cosmo.authentication.log.entity.AdminDeleteLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDeleteLogRepository extends JpaRepository<AdminDeleteLog, Long> {
}
