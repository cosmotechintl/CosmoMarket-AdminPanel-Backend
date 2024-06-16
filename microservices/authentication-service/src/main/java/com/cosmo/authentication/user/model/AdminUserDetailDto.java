package com.cosmo.authentication.user.model;

import com.cosmo.authentication.accessgroup.model.request.FetchAccessGroupDetail;
import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserDetailDto extends ModelBase {
    private String name;
    private String username;
    private String email;
    private StatusDto status;
    private String mobileNumber;
    private String address;
    private String profilePictureName;
    private FetchAccessGroupDetail accessGroup;
}
