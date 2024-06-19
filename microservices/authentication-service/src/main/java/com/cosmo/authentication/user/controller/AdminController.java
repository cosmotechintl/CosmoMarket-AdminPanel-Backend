package com.cosmo.authentication.user.controller;

import com.cosmo.authentication.emailtemplate.model.CreateAdminEmailLog;
import com.cosmo.authentication.user.model.CreateAdminModel;
import com.cosmo.authentication.user.model.FetchAdminDetail;
import com.cosmo.authentication.user.model.request.*;
import com.cosmo.authentication.user.service.AdminService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping(ApiConstant.ADMIN_USER)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping(ApiConstant.CREATE)
   public Mono<ApiResponse> createAdminUser(@RequestBody @Valid CreateAdminModel createAdminModel, CreateAdminEmailLog createAdminEmailLog){
        return adminService.createAdminUser(createAdminModel,createAdminEmailLog);
    }
    @PostMapping()
    public Mono<ApiResponse<?>> getAllAdminUsers(@RequestBody @Valid SearchParam searchParam){
        return adminService.getAllAdminUsers(searchParam);
    }
    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getAdminUserDetails(@RequestBody @Valid FetchAdminDetail fetchAdminDetail){
        return adminService.getAdminUserDetails(fetchAdminDetail);
    }
    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse<?>> updateAdmin(@RequestBody @Valid UpdateAdminRequest request){
        return adminService.updateAdminUser(request);
    }
    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse<?>> deleteAdminUser(@RequestBody DeleteAdminRequest request){
        return adminService.deleteAdminUser(request);
    }
    @PostMapping(ApiConstant.BLOCK)
    public Mono<ApiResponse<?>> blockAdminUser(@RequestBody BlockAdminRequest request){
        return adminService.blockAdminUser(request);
    }
    @PostMapping(ApiConstant.UNBLOCK)
    public Mono<ApiResponse<?>> unblockAdminUser(@RequestBody UnblockAdminUserRequest request){
        return adminService.unblockAdminUser(request);
    }
    @PostMapping(ApiConstant.SET_PASSWORD)
    public Mono<ApiResponse<?>> setPassword(@RequestBody @Valid CreatePasswordRequest createPasswordRequest){
        return adminService.createAdminPassword(createPasswordRequest);
    }
}
