package com.cosmo.authentication.loggedinuser.model;

import com.cosmo.authentication.accessgroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggedInUserDetailDto extends ModelBase {
    private String name;
    private String username;
    private String email;
    private String mobileNumber;
    private StatusDto status;
    private AccessGroupDto accessGroup;
    private String profilePictureName;
}
