package com.cosmo.authentication.reports.customerReport.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchCustomerDetail extends ModelBase {
    @NotBlank(message = "Email is required.")
    private String email;
}
