package com.cosmo.authentication.loggedinuser.service;

import com.cosmo.authentication.loggedinuser.model.PasswordChangeRequest;
import com.cosmo.common.model.ApiResponse;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface UserService {
    Mono<ApiResponse<?>> getLoggedInUserDetail( Principal connectedUser);
    Mono<ApiResponse<?>> changePassword(PasswordChangeRequest passwordChangeRequest, Principal connectedUser);
}
