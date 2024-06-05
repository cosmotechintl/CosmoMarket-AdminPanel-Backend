package com.cosmo.authentication.user.service.impl;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.mapper.AdminMapper;
import com.cosmo.authentication.user.model.AdminDto;
import com.cosmo.authentication.user.repo.AdminRepository;
import com.cosmo.authentication.user.service.AdminService;
import com.cosmo.common.entity.Status;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.util.PaginationUtil;
import com.cosmo.common.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final StatusRepository statusRepository;

    @Override
    @Transactional
    public Admin createAdmin(AdminDto adminDto) {
        Admin admin= adminMapper.mapToEntity(adminDto);
        return adminRepository.save(admin);
    }

    @Override
    public ApiResponse<?> getAllAdminUsers(SearchParam searchParam) {
        Pageable pageable = PaginationUtil.getPageable(searchParam);

        Page<Admin> pagePost= this.adminRepository.findAll(pageable);
        List<Admin> allAdmins= pagePost.getContent();
        if(allAdmins.isEmpty()){
            return ResponseUtil.getNotFoundResponse("admin not found");
        }
        else {
            List<AdminDto> adminDtoList= allAdmins.stream().map(adminMapper::mapToDto).collect(Collectors.toList());
            return ApiResponse.builder().httpStatus(HttpStatus.OK).message("admins fetched successfully").data(adminDtoList).build();
        }
    }

    @Override
    public ApiResponse<?> getAdminByUsername(String username) {
        Optional<Admin> admin= adminRepository.findByUsername(username);
        if(admin.isEmpty()){
            return ResponseUtil.getNotFoundResponse("admin with name "+username+" not found");
        }
        else {
        return ResponseUtil.getSuccessfulApiResponse(admin,"admin fetched successfully");
    }
    }

    @Override
    @Transactional
    public Object updateAdmin(Long id, AdminDto adminDto) {
        Admin existedAdmin= adminRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Admin with id "+id+" not found"));
        Admin updateAdmin = adminMapper.updateAdmin(adminDto,existedAdmin);
        Admin savedAdmin= adminRepository.save(updateAdmin);
        return adminMapper.mapToDto(savedAdmin);
    }

    @Override
    @Transactional
    public void deleteAdmin(String username) {
    Admin admin= adminRepository.findByUsername(username).orElseThrow(()->
            new IllegalArgumentException("Admin does not exist"));
        Status deletedStatus = statusRepository.findByName("DELETED");
        admin.setStatus(deletedStatus);
        adminRepository.save(admin);
    }

}
