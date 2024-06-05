package com.cosmo.authentication.user.controller;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.model.AdminDto;
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

    @PostMapping
    public ResponseEntity<Admin> addAdmin(@RequestBody AdminDto adminDto) {

        return ResponseEntity.ok(adminService.createAdmin(adminDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllAdmins(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ) {
        SearchParam searchParam = new SearchParam();
        searchParam.setFirstRow(pageNo);
        searchParam.setPageSize(pageSize);
        return ResponseEntity.ok().body(adminService.getAllAdminUsers(searchParam));
    }

    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<?>> getAdminById(@PathVariable String username) {
        return ResponseEntity.ok(adminService.getAdminByUsername(username));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<AdminDto>> updateAdmin(@PathVariable Long id, @RequestBody AdminDto adminDto) {
        AdminDto response= (AdminDto)adminService.updateAdmin(id, adminDto);
        ApiResponse<AdminDto> apiResponse= new ApiResponse<>();
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setMessage("admin updated successfully");
        apiResponse.setData(response);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ApiResponse<String>> deleteAdminUser(@PathVariable String username){
        adminService.deleteAdmin(username);
        ApiResponse<String> apiResponse= new ApiResponse<>();
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setMessage("admin user deleted successfully");
        apiResponse.setData(username);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

}
