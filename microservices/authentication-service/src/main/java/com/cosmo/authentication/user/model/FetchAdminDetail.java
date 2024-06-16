package com.cosmo.authentication.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchAdminDetail {
    @NotBlank(message = "username is required.")
    private String username;
}
