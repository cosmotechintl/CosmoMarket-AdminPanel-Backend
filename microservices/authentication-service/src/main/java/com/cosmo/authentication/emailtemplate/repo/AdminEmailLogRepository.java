package com.cosmo.authentication.emailtemplate.repo;

import com.cosmo.authentication.emailtemplate.entity.AdminEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminEmailLogRepository extends JpaRepository<AdminEmailLog,Long> {
}
