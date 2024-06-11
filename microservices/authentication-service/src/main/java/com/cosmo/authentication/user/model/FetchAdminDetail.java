package com.cosmo.authentication.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchAdminDetail {
    @NotBlank(message = "Name is required.")
    private String username;
}
