package com.cosmo.adminservice.reports.vendorReport.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVendorCategoryModel extends ModelBase {

    @NotBlank(message = "Vendor category name cannot be null")
    private String name;

    @NotBlank(message = "Vendor category description cannot be null")
    private String description;
}
