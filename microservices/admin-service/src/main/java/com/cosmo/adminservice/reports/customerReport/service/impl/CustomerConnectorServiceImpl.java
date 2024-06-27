package com.cosmo.adminservice.reports.customerReport.service.impl;

import com.cosmo.adminservice.reports.customerReport.model.request.UnblockCustomerRequest;
import com.cosmo.adminservice.reports.customerReport.model.request.BlockCustomerRequest;
import com.cosmo.adminservice.reports.customerReport.model.request.FetchCustomerDetail;
import com.cosmo.adminservice.reports.customerReport.service.CustomerService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.constant.ServiceConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.service.AbstractConnectorService;
import com.cosmo.authentication.core.config.PropertiesFileValue;
import com.cosmo.common.service.ConnectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Qualifier(ServiceConstant.CUSTOMER_SERVICE)
@RequiredArgsConstructor
@Service
public class CustomerConnectorServiceImpl extends AbstractConnectorService implements CustomerService,ConnectorService {
    private final PropertiesFileValue propertiesFileValue;

    @Override
    protected String getBaseUrl() {
        return propertiesFileValue.getCustomerServiceUrl();
    }

    @Override
    public Mono<ApiResponse<Object>> getRegisteredCustomers(SearchParam searchParam) {
        return connectToService(searchParam,
                ApiConstant.CUSTOMER+ApiConstant.SLASH+ApiConstant.GET,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public Mono<ApiResponse<Object>> blockCustomer(BlockCustomerRequest blockCustomerRequest) {
        return connectToService(blockCustomerRequest,
                ApiConstant.CUSTOMER+ApiConstant.SLASH+ApiConstant.BLOCK,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public Mono<ApiResponse<Object>> getCustomerDetails(FetchCustomerDetail fetchCustomerDetail) {
        return connectToService(fetchCustomerDetail,
                ApiConstant.CUSTOMER+ApiConstant.SLASH+ApiConstant.GET+ApiConstant.SLASH+ApiConstant.DETAIL,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public Mono<ApiResponse<Object>> unblockCustomer(UnblockCustomerRequest unblockCustomerRequest) {
        return connectToService(unblockCustomerRequest,
                ApiConstant.CUSTOMER+ApiConstant.SLASH+ApiConstant.UNBLOCK,
                new ParameterizedTypeReference<>() {
                });
    }
}
