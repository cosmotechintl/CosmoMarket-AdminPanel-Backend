package com.cosmo.authentication.reports.vendorReport.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto extends ModelBase {
    @NotBlank(message = "Name is required")
    private String name;
}
