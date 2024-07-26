package com.cosmo.adminservice.reports.customerReport.service;

import com.cosmo.adminservice.reports.customerReport.model.request.BlockCustomerRequest;
import com.cosmo.adminservice.reports.customerReport.model.request.FetchCustomerDetail;
import com.cosmo.adminservice.reports.customerReport.model.request.UnblockCustomerRequest;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

public interface CustomerService  {

    Mono<ApiResponse<Object>> getRegisteredCustomers(SearchParam searchParam);
    Mono<ApiResponse<Object>> blockCustomer(BlockCustomerRequest blockCustomerRequest);
    Mono<ApiResponse<Object>> getCustomerDetails(FetchCustomerDetail fetchCustomerDetail);
    Mono<ApiResponse<Object>> unblockCustomer(UnblockCustomerRequest unblockCustomerRequest);
}
