package com.cosmo.authentication.user.model.request;

import com.cosmo.authentication.accessgroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdminRequest extends ModelBase {

    private String email;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Mobile Number cannot be null")
    private String mobileNumber;
    @NotBlank(message = "Address cannot be null")
    private String address;
    @NotNull
    private AccessGroupDto accessGroup;

}
