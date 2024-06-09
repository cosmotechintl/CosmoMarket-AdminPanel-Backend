package com.cosmo.authentication.user.controller;

import com.cosmo.authentication.user.model.AuthenticationRequest;
import com.cosmo.authentication.user.service.AuthenticationService;
import com.cosmo.common.constant.ApiConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.AUTHENTICATE)
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping()
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
