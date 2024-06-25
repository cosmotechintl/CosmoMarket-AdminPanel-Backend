package com.cosmo.authentication.user.service;


import com.cosmo.authentication.emailtemplate.model.CreateAdminEmailLog;
import com.cosmo.authentication.log.model.AdminBlockLogModel;
import com.cosmo.authentication.log.model.AdminDeleteLogModel;
import com.cosmo.authentication.user.model.CreateAdminModel;
import com.cosmo.authentication.user.model.FetchAdminDetail;
import com.cosmo.authentication.user.model.request.*;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

import java.security.Principal;


public interface AdminService {

    Mono<ApiResponse> createAdminUser(CreateAdminModel createAdminModel, CreateAdminEmailLog createAdminEmailLog);
    Mono<ApiResponse<?>> getAllAdminUsers(SearchParam searchParam);
    Mono<ApiResponse<?>> getAdminUserDetails(FetchAdminDetail fetchAdminDetail);
    Mono<ApiResponse<?>> updateAdminUser(UpdateAdminRequest updateAdminRequest);
    Mono<ApiResponse<?>> deleteAdminUser(DeleteAdminRequest deleteAdminRequest, AdminDeleteLogModel adminDeleteLogModel);
    Mono<ApiResponse<?>> blockAdminUser(BlockAdminRequest blockAdminRequest, AdminBlockLogModel adminBlockLogModel);
    Mono<ApiResponse<?>> unblockAdminUser(UnblockAdminUserRequest unblockAdminRequest);
    Mono<ApiResponse<?>> createAdminPassword(CreatePasswordRequest createPasswordRequest);
}
