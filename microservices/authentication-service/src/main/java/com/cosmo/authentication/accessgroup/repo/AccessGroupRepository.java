package com.cosmo.authentication.accessgroup.repo;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.common.model.SearchParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessGroupRepository extends JpaRepository<AccessGroup,Long> {
    Optional<AccessGroup> findByName(String name);

}