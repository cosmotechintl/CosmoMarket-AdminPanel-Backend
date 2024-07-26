package com.cosmo.authentication.core.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class PrepareEmailContentDto extends ModelBase {
    @NotBlank(message = "Admin username cannot be empty")
    private String adminUserName;
    @NotBlank(message = "Template name cannot be empty")
    private String templateName;
    @NotNull(message = "Expiration time cannot be null")
    private Date expirationTime;
    @NotBlank(message = "UUID cannot be blank")
    private String uuid;
}
