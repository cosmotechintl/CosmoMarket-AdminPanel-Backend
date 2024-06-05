package com.cosmo.authentication.user.mapper;

import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.model.AdminDto;
import com.cosmo.authentication.user.repo.AccessGroupRepository;
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
            );
        }




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
    public Admin updateAdmin(AdminDto adminDto, Admin existedAdmin) {
        if(adminDto== null){
            throw new IllegalArgumentException("admin cannot be null");
        }
        if(existedAdmin==null){
            return mapToEntity(adminDto);
        }
        existedAdmin.setName(adminDto.getName());
        existedAdmin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        existedAdmin.setUsername(adminDto.getUsername());
        existedAdmin.setActive(adminDto.isActive());
        existedAdmin.setEmail(adminDto.getEmail());
        existedAdmin.setMobileNumber(adminDto.getMobileNumber());
        existedAdmin.setAddress(adminDto.getAddress());
        Status status = statusRepository.findById(adminDto.getStatusId()).orElseThrow(()->
                new ResourceNotFoundException("status does not exist"));
        existedAdmin.setStatus(status);
        AccessGroup accessGroup = accessGroupRepository.findById(adminDto.getAccessGroupId()).orElseThrow(()->
                new ResourceNotFoundException("access group not found"));
        existedAdmin.setAccessGroup(accessGroup);
        existedAdmin.setPasswordChangeDate(adminDto.getPasswordChangeDate());
        existedAdmin.setLastLoggedInTime(adminDto.getLastLoggedInTime());
        existedAdmin.setWrongPasswordAttemptCount(adminDto.getWrongPasswordAttemptCount());
        existedAdmin.setProfilePictureName(adminDto.getProfilePictureName());
        existedAdmin.setOtpAuthSecret(adminDto.getOtpAuthSecret());
        existedAdmin.setTwoFactorEnabled(adminDto.isTwoFactorEnabled());
        existedAdmin.setWrongOtpAuthAttemptCount(adminDto.getWrongOtpAuthAttemptCount());
        existedAdmin.setSuperAdmin(adminDto.isSuperAdmin());

        return existedAdmin;
    }
}
