package com.cosmo.adminservice.accessgroup.repository;

import com.cosmo.authentication.role.entity.AccessGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessGroupRepository extends JpaRepository<AccessGroup,Long> {

}
