package com.cosmo.authentication.user.service;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.model.AdminDto;
import com.cosmo.authentication.user.model.request.AdminUserRequest;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;


public interface AdminService {

    Admin createAdmin(AdminDto adminDto);
    ApiResponse<?> getAllAdminUsers(SearchParam searchParam);
    ApiResponse<?> getAdminByUsername(String username);
    Object updateAdmin(AdminUserRequest adminUserRequest);
    void deleteAdmin(String username);
}
