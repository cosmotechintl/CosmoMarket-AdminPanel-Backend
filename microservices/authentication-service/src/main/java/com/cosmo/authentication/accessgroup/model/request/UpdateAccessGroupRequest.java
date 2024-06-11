package com.cosmo.authentication.accessgroup.model.request;

import com.cosmo.authentication.accessgroup.model.AccessGroupRoleMapDetailDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UpdateAccessGroupRequest
{
    private Long id;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @Valid
    @NotNull
    private List<AccessGroupRoleMapDetailDto> accessGroupRoleMaps;
}