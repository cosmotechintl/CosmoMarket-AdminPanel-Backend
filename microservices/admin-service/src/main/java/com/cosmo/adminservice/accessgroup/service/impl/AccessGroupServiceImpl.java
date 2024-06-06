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
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.util.PaginationUtil;
import com.cosmo.common.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public ApiResponse<?> getAllAccessGroup(SearchParam searchParam) {
        Pageable pageable= PaginationUtil.getPageable(searchParam);
        Page<AccessGroup> pagePost= this.accessGroupRepository.findAll(pageable);

        List<AccessGroup> allAccessGroups= pagePost.getContent();
        if ((allAccessGroups.isEmpty())){
            return ResponseUtil.getNotFoundResponse("access group not found");
        }
        else {
            List<AccessGroupResponse> accessGroupModelList= allAccessGroups.stream().
                    map(accessGroupMapper::entityToDto).collect(Collectors.toList());
            return ApiResponse.builder().httpStatus(HttpStatus.OK).message("access group fetched successfully").data(accessGroupModelList).build();
        }

    }

    @Override
    public ApiResponse<?> getAccessGroupByName(String name) {
        Optional<AccessGroup> accessGroup= accessGroupRepository.findByName(name);
        if (accessGroup.isEmpty()){
            return ResponseUtil.getNotFoundResponse("access group with name "+name+" not found");
        }
        else {
            return ResponseUtil.getSuccessfulApiResponse(accessGroup,"access group fetched successfully");
        }
    }


}
