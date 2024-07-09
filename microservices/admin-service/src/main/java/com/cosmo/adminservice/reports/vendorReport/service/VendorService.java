package com.cosmo.adminservice.reports.vendorReport.service;

import com.cosmo.adminservice.reports.vendorReport.model.request.FetchVendorDetail;
import com.cosmo.adminservice.reports.vendorReport.model.CreateVendorModel;
import com.cosmo.adminservice.reports.vendorReport.model.request.UpdateVendorDetailRequest;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

public interface VendorService {
    Mono<ApiResponse<Object>> createVendor(CreateVendorModel createVendorModel);
    Mono<ApiResponse<Object>> getAllVendors(SearchParam searchParam);
    Mono<ApiResponse<Object>> getVendorDetails(FetchVendorDetail fetchVendorDetail);
    Mono<ApiResponse<Object>> getCategories(SearchParam searchParam);
    Mono<ApiResponse<Object>> updateVendor(UpdateVendorDetailRequest updateVendorDetailRequest);
}
