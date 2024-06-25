package com.cosmo.authentication.log.model;

import com.cosmo.authentication.user.entity.Admin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDeleteLogModel {
    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;

    private Admin admin;
}
