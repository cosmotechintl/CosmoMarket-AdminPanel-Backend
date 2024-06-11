package com.cosmo.authentication.user.service;


import com.cosmo.authentication.user.model.CreateAdminModel;
import com.cosmo.authentication.user.model.FetchAdminDetail;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;


public interface AdminService {

    Mono<ApiResponse> createAdminUser(CreateAdminModel createAdminModel);
    Mono<ApiResponse<?>> getAllAdminUsers(SearchParam searchParam);
    Mono<ApiResponse<?>> getAdminUserDetails(FetchAdminDetail fetchAdminDetail);
}
