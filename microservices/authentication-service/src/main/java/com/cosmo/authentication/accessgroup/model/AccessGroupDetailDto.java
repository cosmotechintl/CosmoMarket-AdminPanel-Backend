package com.cosmo.authentication.accessgroup.model;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.common.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO for {@link AccessGroup}
 */
@Getter
@Setter
public class AccessGroupDetailDto extends ModelBase {

    private String name;
    private String description;
    private TypeDto type;
    private List<AccessGroupRoleMapDetailDto> accessGroupRoleMaps;
}
