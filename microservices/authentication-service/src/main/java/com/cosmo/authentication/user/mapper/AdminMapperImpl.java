package com.cosmo.authentication.user.mapper;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.model.AdminDto;
import com.cosmo.authentication.user.model.request.AdminUserRequest;
import com.cosmo.authentication.accessgroup.repo.AccessGroupRepository;
import com.cosmo.common.entity.Status;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminMapperImpl implements AdminMapper {
        private final StatusRepository statusRepository;
        private final AccessGroupRepository accessGroupRepository;
        private final PasswordEncoder passwordEncoder;

    @Override
    public AdminDto mapToDto(Admin admin) {
            return new AdminDto(
                    admin.getName(),
                    admin.getPassword(),
                    admin.getUsername(),
                    admin.isActive(),
                    admin.getEmail(),
                    admin.getMobileNumber(),
                    admin.getAddress(),
                    admin.getStatus().getId(),
                    admin.getAccessGroup().getId(),
                    admin.getPasswordChangeDate(),
                    admin.getLastLoggedInTime(),
                    admin.getWrongPasswordAttemptCount(),
                    admin.getProfilePictureName(),
                    admin.getOtpAuthSecret(),
                    admin.isTwoFactorEnabled(),
                    admin.getWrongOtpAuthAttemptCount(),
                    admin.isSuperAdmin()
            );}
    @Override
    public Admin mapToEntity(AdminDto adminDto) {
        if ( adminDto == null ) {
            return null;
        }
        Admin admin = new Admin();
        admin.setName(adminDto.getName());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setUsername(adminDto.getUsername());
        admin.setActive(adminDto.isActive());
        admin.setEmail(adminDto.getEmail());
        admin.setMobileNumber(adminDto.getMobileNumber());
        admin.setAddress(adminDto.getAddress());
        admin.setPasswordChangeDate(adminDto.getPasswordChangeDate());
        admin.setLastLoggedInTime(adminDto.getLastLoggedInTime());
        admin.setWrongPasswordAttemptCount(adminDto.getWrongPasswordAttemptCount());
        admin.setProfilePictureName(adminDto.getProfilePictureName());
        admin.setOtpAuthSecret(adminDto.getOtpAuthSecret());
        admin.setTwoFactorEnabled(adminDto.isTwoFactorEnabled());
        admin.setWrongOtpAuthAttemptCount(adminDto.getWrongOtpAuthAttemptCount());
        admin.setSuperAdmin(adminDto.isSuperAdmin());

        Status status = statusRepository.findById(adminDto.getStatusId()).orElseThrow(()->
                new ResourceNotFoundException("invalid status id "+adminDto.getStatusId()));
        admin.setStatus(status);
        AccessGroup accessGroup = accessGroupRepository.findById(adminDto.getAccessGroupId()).orElseThrow(()->
                new ResourceNotFoundException("invalid access group id "+adminDto.getAccessGroupId()));
        admin.setAccessGroup(accessGroup);

        return admin;
    }

    @Override
    public Admin requestToEntity(AdminUserRequest adminDto) {
        if ( adminDto == null ) {
            return null;
        }
        Admin admin = new Admin();
        admin.setName(adminDto.getName());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setUsername(adminDto.getUsername());
        admin.setActive(adminDto.isActive());
        admin.setEmail(adminDto.getEmail());
        admin.setMobileNumber(adminDto.getMobileNumber());
        admin.setAddress(adminDto.getAddress());
        admin.setPasswordChangeDate(adminDto.getPasswordChangeDate());
        admin.setLastLoggedInTime(adminDto.getLastLoggedInTime());
        admin.setWrongPasswordAttemptCount(adminDto.getWrongPasswordAttemptCount());
        admin.setProfilePictureName(adminDto.getProfilePictureName());
        admin.setOtpAuthSecret(adminDto.getOtpAuthSecret());
        admin.setTwoFactorEnabled(adminDto.isTwoFactorEnabled());
        admin.setWrongOtpAuthAttemptCount(adminDto.getWrongOtpAuthAttemptCount());
        admin.setSuperAdmin(adminDto.isSuperAdmin());

        Status status = statusRepository.findById(adminDto.getStatusId()).orElseThrow(()->
                new ResourceNotFoundException("invalid status id "+adminDto.getStatusId()));
        admin.setStatus(status);
        AccessGroup accessGroup = accessGroupRepository.findById(adminDto.getAccessGroupId()).orElseThrow(()->
                new ResourceNotFoundException("invalid access group id "+adminDto.getAccessGroupId()));
        admin.setAccessGroup(accessGroup);

        return admin;
    }

    @Override
    public AdminUserRequest mapToRequest(Admin admin) {
        return new AdminUserRequest(
                admin.getId(),
                admin.getName(),
                admin.getPassword(),
                admin.getUsername(),
                admin.isActive(),
                admin.getEmail(),
                admin.getMobileNumber(),
                admin.getAddress(),
                admin.getStatus().getId(),
                admin.getAccessGroup().getId(),
                admin.getPasswordChangeDate(),
                admin.getLastLoggedInTime(),
                admin.getWrongPasswordAttemptCount(),
                admin.getProfilePictureName(),
                admin.getOtpAuthSecret(),
                admin.isTwoFactorEnabled(),
                admin.getWrongOtpAuthAttemptCount(),
                admin.isSuperAdmin()
        );}

    @Override
    public Admin updateAdmin(AdminUserRequest adminUserRequest, Admin existedAdmin) {
        if(adminUserRequest== null){
            throw new IllegalArgumentException("admin cannot be null");
        }
        if(existedAdmin==null){
            return requestToEntity(adminUserRequest);
        }
        existedAdmin.setName(adminUserRequest.getName());
        existedAdmin.setPassword(passwordEncoder.encode(adminUserRequest.getPassword()));
        existedAdmin.setUsername(adminUserRequest.getUsername());
        existedAdmin.setActive(adminUserRequest.isActive());
        existedAdmin.setEmail(adminUserRequest.getEmail());
        existedAdmin.setMobileNumber(adminUserRequest.getMobileNumber());
        existedAdmin.setAddress(adminUserRequest.getAddress());
        Status status = statusRepository.findById(adminUserRequest.getStatusId()).orElseThrow(()->
                new ResourceNotFoundException("status does not exist"));
        existedAdmin.setStatus(status);
        AccessGroup accessGroup = accessGroupRepository.findById(adminUserRequest.getAccessGroupId()).orElseThrow(()->
                new ResourceNotFoundException("access group not found"));
        existedAdmin.setAccessGroup(accessGroup);
        existedAdmin.setPasswordChangeDate(adminUserRequest.getPasswordChangeDate());
        existedAdmin.setLastLoggedInTime(adminUserRequest.getLastLoggedInTime());
        existedAdmin.setWrongPasswordAttemptCount(adminUserRequest.getWrongPasswordAttemptCount());
        existedAdmin.setProfilePictureName(adminUserRequest.getProfilePictureName());
        existedAdmin.setOtpAuthSecret(adminUserRequest.getOtpAuthSecret());
        existedAdmin.setTwoFactorEnabled(adminUserRequest.isTwoFactorEnabled());
        existedAdmin.setWrongOtpAuthAttemptCount(adminUserRequest.getWrongOtpAuthAttemptCount());
        existedAdmin.setSuperAdmin(adminUserRequest.isSuperAdmin());
        return existedAdmin;
    }
}
