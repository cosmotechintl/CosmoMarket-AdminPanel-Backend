package com.cosmo.authentication.user.service;


import com.cosmo.authentication.user.model.CreateAdminModel;
import com.cosmo.authentication.user.model.FetchAdminDetail;
import com.cosmo.authentication.user.model.request.BlockAdminRequest;
import com.cosmo.authentication.user.model.request.DeleteAdminRequest;
import com.cosmo.authentication.user.model.request.UnblockAdminUserRequest;
import com.cosmo.authentication.user.model.request.UpdateAdminRequest;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

import java.security.Principal;


public interface AdminService {

    Mono<ApiResponse> createAdminUser(CreateAdminModel createAdminModel);
    Mono<ApiResponse<?>> getAllAdminUsers(SearchParam searchParam);
    Mono<ApiResponse<?>> getAdminUserDetails(FetchAdminDetail fetchAdminDetail);
    Mono<ApiResponse<?>> updateAdminUser(UpdateAdminRequest updateAdminRequest, Principal connectedUser);
    Mono<ApiResponse<?>> deleteAdminUser(DeleteAdminRequest deleteAdminRequest);
    Mono<ApiResponse<?>> blockAdminUser(BlockAdminRequest blockAdminRequest);
    Mono<ApiResponse<?>> unblockAdminUser(UnblockAdminUserRequest unblockAdminRequest);
}
