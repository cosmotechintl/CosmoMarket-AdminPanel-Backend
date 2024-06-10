package com.cosmo.authentication.loggedinuser.service.impl;

import com.cosmo.authentication.loggedinuser.mapper.LoggedInUserMapper;
import com.cosmo.authentication.loggedinuser.model.LoggedInUserDetailDto;
import com.cosmo.authentication.loggedinuser.model.PasswordChangeRequest;
import com.cosmo.authentication.loggedinuser.service.UserService;
import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.repo.AdminRepository;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.security.Principal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AdminRepository adminRepository;
    private final LoggedInUserMapper loggedInUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<ApiResponse<?>> getLoggedInUserDetail(Principal connectedUser) {
        var adminUser= ((Admin)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
            LoggedInUserDetailDto loggedInUserDetailDto = loggedInUserMapper.getLoggedInUserDetailDto(adminUser);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(loggedInUserDetailDto,"logged in user fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> changePassword(PasswordChangeRequest passwordChangeRequest, Principal connectedUser) {

        var admin= ((Admin) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());

        if (!passwordEncoder.matches(passwordChangeRequest.getOldPassword(),admin.getPassword())){
                return Mono.just(ResponseUtil.getFailureResponse("old password is incorrect"));
        }
        if(!passwordChangeRequest.getNewPassword().equals((passwordChangeRequest.getRetypeNewPassword()))){
              return Mono.just(ResponseUtil.getFailureResponse("new passwords do not match"));
        }
        else {
            admin.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
            admin.setPasswordChangeDate(new Date());
            adminRepository.save(admin);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("password changed successfully"));
        }
    }
}
