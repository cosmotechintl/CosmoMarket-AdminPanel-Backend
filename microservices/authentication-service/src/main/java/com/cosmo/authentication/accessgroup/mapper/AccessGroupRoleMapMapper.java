package com.cosmo.authentication.accessgroup.mapper;

import com.cosmo.authentication.accessgroup.model.AssignRoleModel;
import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.accessgroup.entity.AccessGroupRoleMap;
import com.cosmo.authentication.accessgroup.repo.AccessGroupRoleMapRepository;
import com.cosmo.authentication.role.entity.Roles;
import com.cosmo.authentication.accessgroup.model.AccessGroupRoleMapDto;
import com.cosmo.authentication.role.repository.RolesRepository;
import com.cosmo.authentication.role.service.RolesService;
import com.cosmo.common.exception.ResourceNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AccessGroupRoleMapMapper {

    @Autowired
    protected RolesService rolesService;

    @Autowired
    AccessGroupRoleMapRepository accessGroupRoleMapRepository;
    @Autowired
    private RolesRepository rolesRepository;

    public List<AccessGroupRoleMap> createAccessGroupRoleMap(AccessGroup accessGroup, List<AssignRoleModel> roles) {
        List<Long> assignedRoleId = roles.stream().map(AssignRoleModel::getRoleId).toList();
        List<Roles> allRoles = rolesService.getAllRoles();
        List<AccessGroupRoleMap> accessGroupRoleMaps = allRoles.stream().map(role -> {
            AccessGroupRoleMap accessGroupRoleMap = new AccessGroupRoleMap();
            accessGroupRoleMap.setAccessGroup(accessGroup);
            accessGroupRoleMap.setIsActive(assignedRoleId.contains(role.getId()));
            accessGroupRoleMap.setRoles(role);
            return accessGroupRoleMap;
        }).collect(Collectors.toList());

        return accessGroupRoleMapRepository.saveAll(accessGroupRoleMaps);
    }
    public List<AccessGroupRoleMap> updateAccessGroupRoleMap(AccessGroup accessGroup, List<AssignRoleModel> roles) {
        List<Long> assignedRoleId = roles.stream().map(AssignRoleModel::getRoleId).toList();
        List<AccessGroupRoleMap> existingRoleMaps= accessGroupRoleMapRepository.findByAccessGroup(accessGroup);

        Map<Long, AccessGroupRoleMap> existingRoleMapById = existingRoleMaps.stream()
                .collect(Collectors.toMap(roleMap -> roleMap.getRoles().getId(), roleMap -> roleMap));

        List<AccessGroupRoleMap> updatedRoleMaps = new ArrayList<>();
        for (Long roleId : assignedRoleId) {
            AccessGroupRoleMap roleMap = existingRoleMapById.get(roleId);
            if (roleMap != null) {
                roleMap.setIsActive(true);
                updatedRoleMaps.add(roleMap);
            } else {
                Roles role = rolesRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
                AccessGroupRoleMap newRoleMap = new AccessGroupRoleMap();
                newRoleMap.setAccessGroup(accessGroup);
                newRoleMap.setRoles(role);
                newRoleMap.setIsActive(true);
                updatedRoleMaps.add(newRoleMap);
            }
        }
        existingRoleMaps.stream()
                .filter(roleMap -> !updatedRoleMaps.contains(roleMap))
                .forEach(updatedRoleMaps::add);

        return accessGroupRoleMapRepository.saveAll(updatedRoleMaps);

    }

    public abstract AccessGroupRoleMap toEntity(AccessGroupRoleMapDto accessGroupRoleMapDto);

    public abstract AccessGroupRoleMapDto toDto(AccessGroupRoleMap accessGroupRoleMap);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract AccessGroupRoleMap partialUpdate(AccessGroupRoleMapDto accessGroupRoleMapDto, @MappingTarget AccessGroupRoleMap accessGroupRoleMap);
}