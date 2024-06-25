package com.cosmo.authentication.log.repo;

import com.cosmo.authentication.log.entity.AdminBlockLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminBlockLogRepository extends JpaRepository<AdminBlockLog, Long> {
}
