package com.cosmo.adminservice.accessgroup.service.impl;

import com.cosmo.adminservice.accessgroup.mapper.AccessGroupMapper;
import com.cosmo.adminservice.accessgroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessgroup.model.AccessGroupResponse;
import com.cosmo.authentication.user.repo.AccessGroupRepository;
import com.cosmo.adminservice.accessgroup.service.AccessGroupService;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.authentication.role.entity.AccessGroupRoleMap;
import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.authentication.role.repository.RolesRepository;
import com.cosmo.common.entity.Status;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.repository.StatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessGroupServiceImpl implements AccessGroupService {
    private final AccessGroupRepository accessGroupRepository;
    private final AccessGroupMapper accessGroupMapper;
    private final StatusRepository statusRepository;
    private final RolesRepository rolesRepository;


    @Transactional
    @Override
    public AccessGroupResponse createAccessGroup(AccessGroupModel accessGroupModel){
        AccessGroup accessGroup= accessGroupMapper.toEntity(accessGroupModel);
        Status status= statusRepository.findById(accessGroupModel.getStatus().getId()).orElse(null);
        accessGroup.setStatus(status);
        List<AccessGroupRoleMap> accessGroupRoleMaps = new ArrayList<>();
        for(Long roleId : accessGroupModel.getRoleId()) {
            Roles role = rolesRepository.findById(roleId).orElse(null);
            AccessGroupRoleMap roleMap = new AccessGroupRoleMap();
            roleMap.setRoles(role);
            roleMap.setAccessGroup(accessGroup);
            roleMap.setIsActive(true); // Set default value for isActive
            accessGroupRoleMaps.add(roleMap);
        }
        accessGroup.setAccessGroupRoleMaps(accessGroupRoleMaps);
        AccessGroup savedAccessGroup = accessGroupRepository.save(accessGroup);
        return accessGroupMapper.entityToDto(savedAccessGroup);
    }

    @Override
    public AccessGroup updateAccessGroup(Long id, AccessGroup accessGroup) {
        return null;
    }

    @Override
    public void deleteAccessGroup(Long id) {

    }

    @Override
    public List<AccessGroup> getAllAccessGroup() {
        return accessGroupRepository.findAll();
    }

    @Override
    public AccessGroup getAccessgroup(Long id) {
        Optional<AccessGroup> accessGroup= accessGroupRepository.findById(id);
        return accessGroup.orElseThrow(()->
                new ResourceNotFoundException("access group not found"));
    }
}
