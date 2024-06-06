package com.cosmo.adminservice.accessgroup.service;

import com.cosmo.adminservice.accessgroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessgroup.model.AccessGroupResponse;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;

import java.util.List;

public interface AccessGroupService {
        AccessGroupResponse createAccessGroup(AccessGroupModel accessGroupModel);
        AccessGroup updateAccessGroup(Long id, AccessGroup accessGroup);
        void deleteAccessGroup(Long id);
        ApiResponse<?> getAllAccessGroup(SearchParam searchParam);
        ApiResponse<?> getAccessGroupByName(String name);
}
