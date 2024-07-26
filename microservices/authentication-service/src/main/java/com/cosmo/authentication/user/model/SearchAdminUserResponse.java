package com.cosmo.authentication.user.model;

import com.cosmo.authentication.accessgroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAdminUserResponse extends ModelBase {
    private String name;
    private String email;
    private String mobileNumber;
    private AccessGroupDto accessGroup;
    private StatusDto status;
}
