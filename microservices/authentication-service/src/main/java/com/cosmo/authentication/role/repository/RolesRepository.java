package com.cosmo.authentication.role.repository;

import com.cosmo.authentication.role.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findByName(String name);
}
