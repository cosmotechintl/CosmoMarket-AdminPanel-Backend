package com.cosmo.authentication.user.mapper;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.model.AdminDto;

public interface AdminMapper {
    AdminDto mapToDto(Admin admin);
    Admin mapToEntity(AdminDto adminDto);
    Admin updateAdmin(AdminDto adminDto, Admin existedAdmin);
}
