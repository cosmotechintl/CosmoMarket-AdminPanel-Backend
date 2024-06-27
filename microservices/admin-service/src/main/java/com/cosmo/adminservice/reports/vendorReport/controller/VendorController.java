package com.cosmo.adminservice.reports.vendorReport.controller;

import com.cosmo.adminservice.reports.vendorReport.model.request.FetchVendorDetail;
import com.cosmo.adminservice.reports.vendorReport.model.CreateVendorModel;
import com.cosmo.adminservice.reports.vendorReport.service.VendorService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.VENDOR)
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<Object>> createVendor(@RequestBody @Valid CreateVendorModel createVendorModel){
        return vendorService.createVendor(createVendorModel);
    }
    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<Object>>getAllVendors(@RequestBody @Valid SearchParam searchParam){
        return vendorService.getAllVendors(searchParam);
    }
    @PostMapping(ApiConstant.GET+ApiConstant.SLASH+ApiConstant.DETAIL)
    public Mono<ApiResponse<Object>> getVendorDetails(@RequestBody @Valid FetchVendorDetail fetchVendorDetail){
        return vendorService.getVendorDetails(fetchVendorDetail);
    }
}
