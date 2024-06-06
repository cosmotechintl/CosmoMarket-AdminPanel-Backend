package com.cosmo.authentication.user.repo;

import com.cosmo.authentication.role.entity.AccessGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessGroupRepository extends JpaRepository<AccessGroup,Long> {
    Optional<AccessGroup> findByName(String name);

}
