package com.cosmo.adminservice.accessgroup.mapper;

import com.cosmo.adminservice.accessgroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessgroup.model.AccessGroupResponse;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.authentication.role.repository.RolesRepository;
import com.cosmo.common.entity.Status;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessGroupMapper {
    private final StatusRepository statusRepository;
    private final RolesRepository rolesRepository;

    public AccessGroup toEntity(AccessGroupModel accessGroupModel) {
        AccessGroup accessGroup = new AccessGroup();
        accessGroup.setName(accessGroupModel.getName());
        accessGroup.setDescription(accessGroupModel.getDescription());
        accessGroup.setCreatedAt(accessGroupModel.getCreatedAt());
        accessGroup.setUpdatedAt(accessGroupModel.getUpdatedAt());
        accessGroup.setSuperAdminGroup(accessGroupModel.isSuperAdminGroup());
        accessGroup.setRemarks(accessGroupModel.getRemarks());

        if(accessGroupModel.getStatusId()!= null){
            Status status= statusRepository.findById(accessGroupModel.getStatusId()).orElseThrow(()->new ResourceNotFoundException("status id does not exist"));
            accessGroup.setStatus(status);
        }

        return accessGroup;
    }
    public AccessGroupResponse entityToDto(AccessGroup accessGroup){
        AccessGroupResponse response= new AccessGroupResponse();
        response.setName(accessGroup.getName());
        response.setDescription(accessGroup.getDescription());
        response.setCreatedAt(accessGroup.getCreatedAt());
        response.setUpdatedAt(accessGroup.getUpdatedAt());
        response.setStatusName(accessGroup.getStatus().getName());
        response.setIsSuperAdminGroup(accessGroup.isSuperAdminGroup());
        response.setRemarks(accessGroup.getRemarks());
        response.setRoleNames(accessGroup.getAccessGroupRoleMaps().stream()
                .map(accessGroupRoleMap -> accessGroupRoleMap.getRoles().getName())
                .collect(Collectors.toList()));
        return response;
    }
}
