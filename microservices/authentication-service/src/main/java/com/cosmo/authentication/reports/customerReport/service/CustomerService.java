package com.cosmo.authentication.reports.customerReport.service;

import com.cosmo.authentication.reports.customerReport.model.request.BlockCustomerRequest;
import com.cosmo.authentication.reports.customerReport.model.request.FetchCustomerDetail;
import com.cosmo.authentication.reports.customerReport.model.request.UnblockCustomerRequest;
import com.cosmo.authentication.reports.customerReport.model.response.SearchCustomerResponse;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.ResponseUtil;
import reactor.core.publisher.Mono;

public interface CustomerService  {

    Mono<ApiResponse<Object>> getRegisteredCustomers(SearchParam searchParam);
    Mono<ApiResponse<Object>> blockCustomer(BlockCustomerRequest blockCustomerRequest);
    Mono<ApiResponse<Object>> getCustomerDetails(FetchCustomerDetail fetchCustomerDetail);
    Mono<ApiResponse<Object>> unblockCustomer(UnblockCustomerRequest unblockCustomerRequest);
}
