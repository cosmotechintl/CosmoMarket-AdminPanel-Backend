package com.cosmo.adminservice.reports.customerReport.controller;

import com.cosmo.adminservice.reports.customerReport.service.CustomerService;
import com.cosmo.adminservice.reports.customerReport.model.response.SearchCustomerResponse;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<SearchCustomerResponse>> getRegisteredCustomers(@RequestBody SearchParam searchParam) {
        return customerService.getRegisteredCustomers(searchParam);
    }
}
