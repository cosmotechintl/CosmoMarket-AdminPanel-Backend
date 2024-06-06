package com.cosmo.authentication.user.mapper;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.model.AdminDto;
import com.cosmo.authentication.user.model.request.AdminUserRequest;

public interface AdminMapper {
    AdminDto mapToDto(Admin admin);
    Admin mapToEntity(AdminDto adminDto);
    Admin updateAdmin(AdminUserRequest adminUserRequest, Admin existedAdmin);
    Admin requestToEntity(AdminUserRequest adminUserRequest);
    AdminUserRequest mapToRequest(Admin admin);
}
