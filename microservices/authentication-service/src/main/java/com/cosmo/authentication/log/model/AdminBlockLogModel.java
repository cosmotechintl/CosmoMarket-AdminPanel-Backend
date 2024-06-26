package com.cosmo.authentication.log.model;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminBlockLogModel extends ModelBase {
    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;

    private Admin admin;
}
