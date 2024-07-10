package com.cosmo.adminservice.reports.vendorReport.controller;

import com.cosmo.adminservice.reports.vendorReport.model.CreateVendorCategoryModel;
import com.cosmo.adminservice.reports.vendorReport.model.request.FetchVendorDetail;
import com.cosmo.adminservice.reports.vendorReport.model.CreateVendorModel;
import com.cosmo.adminservice.reports.vendorReport.model.request.UpdateVendorDetailRequest;
import com.cosmo.adminservice.reports.vendorReport.service.VendorService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping(ApiConstant.VENDOR_CATEGORY+ApiConstant.SLASH+ApiConstant.GET)
    public Mono<ApiResponse<Object>> getCategories(@RequestBody @Valid SearchParam searchParam){
        return vendorService.getCategories(searchParam);
    }
    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse<Object>> updateVendor(@RequestBody @Valid UpdateVendorDetailRequest request){
        return vendorService.updateVendor(request);
    }
    @PostMapping(ApiConstant.VENDOR_CATEGORY+ApiConstant.SLASH+ApiConstant.CREATE)
    public Mono<ApiResponse<Object>> createVendorCategory(@RequestBody @Valid CreateVendorCategoryModel categoryModel){
        return vendorService.createVendorCategory(categoryModel);
    }
}
