package com.cosmo.adminservice.accessgroup.service;

import com.cosmo.adminservice.accessgroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessgroup.model.AccessGroupResponse;
import com.cosmo.authentication.role.entity.AccessGroup;

import java.util.List;

public interface AccessGroupService {
        AccessGroupResponse createAccessGroup(AccessGroupModel accessGroupModel);
        AccessGroup updateAccessGroup(Long id, AccessGroup accessGroup);
        void deleteAccessGroup(Long id);
        List<AccessGroup> getAllAccessGroup();
        AccessGroup getAccessgroup(Long id);
}
