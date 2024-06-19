package com.cosmo.authentication.user.repo.impl;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.repo.AdminUserSearchRepository;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.SearchParamUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cosmo.common.constant.SearchParamConstant.NAME;
import static com.cosmo.common.constant.SearchParamConstant.STATUS;

@Repository
@RequiredArgsConstructor
public class AdminUserSearchRepositoryImpl implements AdminUserSearchRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(a.id) " +
                        "from Admin  a " +
                        "join Status s on s.id=a.status.id " +
                        " where " +
                        "(:name is null or a.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<Admin> getAll(SearchParam searchParam) {
        return em.createQuery("select a " +
                        "from Admin  a " +
                        "join Status s on s.id=a.status.id " +
                        " where " +
                        "(:name is null or a.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getResultList();
    }
    }