package com.cosmo.authentication.user.model;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.accessgroup.model.AccessGroupDto;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminModel extends ModelBase {

        @NotBlank(message = "Name Cannot Be Blank")
        private String name;
        @NotBlank(message = "Mobile Number Cannot Be Blank")
        private String mobileNumber;
        @NotBlank(message = "Address Cannot Be Blank")
        private String address;
        @Email
        @NotBlank(message = "Email Cannot Be Null")
        private String email;
        @NotNull(message = "Access Group Cannot Be Null")
        private AccessGroupDto accessGroup;
}
