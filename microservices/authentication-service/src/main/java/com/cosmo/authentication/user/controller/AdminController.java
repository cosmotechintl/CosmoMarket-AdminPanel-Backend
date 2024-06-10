package com.cosmo.authentication.user.controller;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.model.AdminDto;
import com.cosmo.authentication.user.model.request.AdminUserRequest;
import com.cosmo.authentication.user.model.request.UsernameRequest;
import com.cosmo.authentication.user.service.AdminService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.ADMIN_USER)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping(ApiConstant.CREATE)
    public ResponseEntity<Admin> addAdmin(@RequestBody AdminDto adminDto) {

        return ResponseEntity.ok(adminService.createAdmin(adminDto));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<?>> getAllAdmins(@RequestBody SearchParam searchParam) {

        return ResponseEntity.ok().body(adminService.getAllAdminUsers(searchParam));
    }

    @GetMapping(ApiConstant.GET)
    public ResponseEntity<ApiResponse<?>> getAdminByUsername(@RequestBody UsernameRequest usernameRequest) {
        String username = usernameRequest.getUsername();
        return ResponseEntity.ok(adminService.getAdminByUsername(username));
    }

    @PostMapping(ApiConstant.EDIT)
    public ResponseEntity<ApiResponse<AdminUserRequest>> updateAdmin(@RequestBody AdminUserRequest adminUserRequest) {
        AdminUserRequest response= (AdminUserRequest) adminService.updateAdmin(adminUserRequest);
        ApiResponse<AdminUserRequest> apiResponse= new ApiResponse<>();
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setMessage("admin updated successfully");
        apiResponse.setData(response);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping(ApiConstant.DELETE)
    public ResponseEntity<ApiResponse<String>> deleteAdminUser(@RequestBody UsernameRequest usernameRequest){
        String username = usernameRequest.getUsername();
        adminService.deleteAdmin(username);
        ApiResponse<String> apiResponse= new ApiResponse<>();
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setMessage("admin user deleted successfully");
        apiResponse.setData(username);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

}
