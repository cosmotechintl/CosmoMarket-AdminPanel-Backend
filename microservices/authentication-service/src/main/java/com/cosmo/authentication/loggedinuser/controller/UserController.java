package com.cosmo.authentication.loggedinuser.controller;

import com.cosmo.authentication.loggedinuser.model.PasswordChangeRequest;
import com.cosmo.authentication.loggedinuser.service.UserService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.ADMIN_USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(ApiConstant.GET + ApiConstant.SLASH + ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getLoggedInUserDetail(
            Principal connectedUser
            ){
        return userService.getLoggedInUserDetail(connectedUser);
    }
    @PostMapping(ApiConstant.CHANGE_PASSWORD)
    public Mono<ApiResponse<?>> changePassword(
            @RequestBody @Valid PasswordChangeRequest passwordChangeRequest,
            Principal connectedUser
            ){
        return userService.changePassword(passwordChangeRequest, connectedUser);
    }

}
