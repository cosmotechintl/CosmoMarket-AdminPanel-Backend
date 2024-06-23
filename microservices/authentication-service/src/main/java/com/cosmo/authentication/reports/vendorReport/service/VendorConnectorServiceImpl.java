package com.cosmo.authentication.reports.vendorReport.service;

import com.cosmo.common.constant.ServiceConstant;
import com.cosmo.common.service.AbstractConnectorService;
import com.cosmo.common.service.ConnectorService;
import com.cosmo.authentication.core.config.PropertiesFileValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

@Slf4j
@Qualifier(ServiceConstant.VENDOR_SERVICE)
@RequiredArgsConstructor
public class VendorConnectorServiceImpl extends AbstractConnectorService implements ConnectorService {
    private final PropertiesFileValue propertiesFileValue;

    @Override
    protected String getBaseUrl() {
        return propertiesFileValue.getVendorServiceUrl();
    }

}
