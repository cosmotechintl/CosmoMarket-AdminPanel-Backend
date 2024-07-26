package com.cosmo.adminservice.reports.customerReport.controller;

import com.cosmo.adminservice.reports.customerReport.model.request.FetchCustomerDetail;
import com.cosmo.adminservice.reports.customerReport.model.request.UnblockCustomerRequest;
import com.cosmo.adminservice.reports.customerReport.model.request.BlockCustomerRequest;
import com.cosmo.adminservice.reports.customerReport.service.CustomerService;
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
    public Mono<ApiResponse<Object>> getRegisteredCustomers(@RequestBody SearchParam searchParam) {
        return customerService.getRegisteredCustomers(searchParam);
    }
    @PostMapping(ApiConstant.BLOCK)
    public Mono<ApiResponse<Object>> blockRegisteredCustomers(@RequestBody BlockCustomerRequest blockCustomerRequest) {
        return customerService.blockCustomer(blockCustomerRequest);
    }
    @PostMapping(ApiConstant.GET+ApiConstant.SLASH+ApiConstant.DETAIL)
    public Mono<ApiResponse<Object>> getCustomerDetails(FetchCustomerDetail fetchCustomerDetail){
        return customerService.getCustomerDetails(fetchCustomerDetail);
    }
    @PostMapping(ApiConstant.UNBLOCK)
    public Mono<ApiResponse<Object>> unblockCustomer(UnblockCustomerRequest unblockCustomerRequest){
        return customerService.unblockCustomer(unblockCustomerRequest);
    }
}
