package com.cosmo.authentication.reports.customerReport.service;

import com.cosmo.authentication.reports.customerReport.model.response.SearchCustomerResponse;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

public interface CustomerService  {

    Mono<ApiResponse<SearchCustomerResponse>> getRegisteredCustomers(SearchParam searchParam);
}
