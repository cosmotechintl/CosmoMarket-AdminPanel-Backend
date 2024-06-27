package com.cosmo.authentication.reports.vendorReport.service.impl;

import com.cosmo.authentication.reports.vendorReport.model.CreateVendorModel;
import com.cosmo.authentication.reports.vendorReport.model.VendorDeleteLogModel;
import com.cosmo.authentication.reports.vendorReport.model.request.DeleteVendorRequest;
import com.cosmo.authentication.reports.vendorReport.model.request.FetchVendorDetail;
import com.cosmo.authentication.reports.vendorReport.service.VendorService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.constant.ServiceConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.service.AbstractConnectorService;
import com.cosmo.common.service.ConnectorService;
import com.cosmo.authentication.core.config.PropertiesFileValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Qualifier(ServiceConstant.VENDOR_SERVICE)
@RequiredArgsConstructor
@Service
public class VendorConnectorServiceImpl extends AbstractConnectorService implements ConnectorService, VendorService {
    private final PropertiesFileValue propertiesFileValue;

    @Override
    protected String getBaseUrl() {
        return propertiesFileValue.getVendorServiceUrl();
    }

    @Override
    public Mono<ApiResponse<Object>> createVendor(CreateVendorModel createVendorModel) {
        return connectToService(createVendorModel,
                ApiConstant.VENDOR+ApiConstant.SLASH+ApiConstant.CREATE,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public Mono<ApiResponse<Object>> getAllVendors(SearchParam searchParam) {
        return connectToService(searchParam,
                ApiConstant.VENDOR+ApiConstant.SLASH+ApiConstant.GET,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public Mono<ApiResponse<Object>> getVendorDetails(FetchVendorDetail fetchVendorDetail) {
        return connectToService(fetchVendorDetail,
                ApiConstant.VENDOR+ApiConstant.SLASH+ApiConstant.GET+ApiConstant.SLASH+ApiConstant.DETAIL,
                new ParameterizedTypeReference<>() {
                });
    }


}
