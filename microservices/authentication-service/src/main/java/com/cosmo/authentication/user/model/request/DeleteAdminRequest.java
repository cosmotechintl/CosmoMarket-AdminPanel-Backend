package com.cosmo.authentication.user.model.request;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteAdminRequest extends ModelBase {
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;
}
