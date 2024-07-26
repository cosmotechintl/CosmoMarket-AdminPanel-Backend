package com.cosmo.adminservice.reports.vendorReport.model;

import com.cosmo.common.model.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorDeleteLogModel extends ModelBase {

    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;
}
