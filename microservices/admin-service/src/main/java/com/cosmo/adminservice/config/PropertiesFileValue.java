package com.cosmo.adminservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class PropertiesFileValue {

    @Value("${customer_service.url}")
    private String customerServiceUrl;

    @Value("${vendor_service.url}")
    private String vendorServiceUrl;
}
