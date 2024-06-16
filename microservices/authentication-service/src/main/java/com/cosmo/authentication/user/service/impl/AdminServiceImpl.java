package com.cosmo.authentication.user.service.impl;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.mapper.AdminMapper;
import com.cosmo.authentication.user.model.AdminUserDetailDto;
import com.cosmo.authentication.user.model.CreateAdminModel;
import com.cosmo.authentication.user.model.FetchAdminDetail;
import com.cosmo.authentication.user.model.SearchAdminUserResponse;
import com.cosmo.authentication.user.model.request.BlockAdminRequest;
import com.cosmo.authentication.user.model.request.DeleteAdminRequest;
import com.cosmo.authentication.user.model.request.UnblockAdminUserRequest;
import com.cosmo.authentication.user.model.request.UpdateAdminRequest;
import com.cosmo.authentication.user.repo.AdminRepository;
import com.cosmo.authentication.user.repo.AdminUserSearchRepository;
import com.cosmo.authentication.user.service.AdminService;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final SearchResponse searchResponse;
    private final AdminUserSearchRepository adminUserSearchRepository;
    private final StatusRepository statusRepository;

    @Override
    @Transactional
    public Mono<ApiResponse> createAdminUser(CreateAdminModel createAdminModel) {
        Optional<Admin> existedAdminUser = adminRepository.findByUsername(createAdminModel.getEmail());
        Optional<Admin> existedNumber= adminRepository.findByMobileNumber(createAdminModel.getMobileNumber());
        if (existedAdminUser.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("This email is already linked to another account. Please use a different email"));
        }
        if (existedNumber.isPresent()){
            return Mono.just(ResponseUtil.getFailureResponse("The entered mobile number is already linked to another account. Please use a different number"));
        }
        Admin admin = adminMapper.mapToEntity(createAdminModel);
          adminRepository.save(admin);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin User Created Successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> getAllAdminUsers(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Admin, SearchAdminUserResponse> responseBuilder = SearchResponseWithMapperBuilder
                .<Admin,SearchAdminUserResponse>builder()
                .count(adminUserSearchRepository::count)
                .searchData(adminUserSearchRepository::getAll)
                .mapperFunction(this.adminMapper::getAdminUserResponses)
                .searchParam(searchParam)
                .build();
        PageableResponse<SearchAdminUserResponse> response= searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response,"Admin users fetched successfully"));

    }

    @Override
    public Mono<ApiResponse<?>> getAdminUserDetails(FetchAdminDetail fetchAdminDetail) {
        Optional<Admin> admin = adminRepository.findByEmail(fetchAdminDetail.getEmail());
        if (admin.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin not found"));
        }
        else {
            AdminUserDetailDto adminUserDetailDto = adminMapper.getAdminUserDetails(admin.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(adminUserDetailDto,"Admin user fetched successfully"));
        }
    }
    @Override
    public Mono<ApiResponse<?>> updateAdminUser(UpdateAdminRequest updateAdminRequest, Principal connectedUser) {
        var adminUser= ((Admin)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        Optional<Admin> existedNumber= adminRepository.findByMobileNumber(updateAdminRequest.getMobileNumber());
        if (existedNumber.isPresent() && !existedNumber.get().getEmail().equals(updateAdminRequest.getEmail())){
            return Mono.just(ResponseUtil.getFailureResponse("The mobile number is linked to another account."));
        }
        Optional<Admin> admin= adminRepository.findByEmail(updateAdminRequest.getEmail());
        Admin admin1= admin.get();
        if ("BLOCKED".equals(admin1.getStatus().getName()) ||"DELETED".equals(admin1.getStatus().getName())){
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        }
        else {
            Admin updatedAdmin = adminMapper.updateAdminUser(updateAdminRequest, adminUser);
            adminRepository.save(updatedAdmin);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user updated successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> deleteAdminUser(DeleteAdminRequest deleteAdminRequest) {
        Optional<Admin> admin= adminRepository.findByEmail(deleteAdminRequest.getEmail());
        if (admin.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        }
        else {
            Admin admin1= admin.get();
            if ("BLOCKED".equals(admin1.getStatus().getName()) ||"DELETED".equals(admin1.getStatus().getName())){
                return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
            }
            admin1.setStatus(statusRepository.findByName("DELETED"));
            adminRepository.save(admin1);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user deleted successfully"));
        }}

    @Override
    public Mono<ApiResponse<?>> blockAdminUser(BlockAdminRequest blockAdminRequest) {
        Optional<Admin> admin= adminRepository.findByEmail(blockAdminRequest.getEmail());
        if (admin.isEmpty() ){
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        }
        else {
            Admin admin1= admin.get();
            if("DELETED".equals(admin1.getStatus().getName()) || "BLOCKED".equals(admin1.getStatus().getName()) ){
                return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
            }
            else {
            admin1.setStatus(statusRepository.findByName("BLOCKED"));
            adminRepository.save(admin1);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user blocked successfully"));
        }}
    }

    @Override
    public Mono<ApiResponse<?>> unblockAdminUser(UnblockAdminUserRequest unblockAdminRequest) {
        Optional<Admin> admin=  adminRepository.findByEmail(unblockAdminRequest.getEmail());
        Admin admin1 = admin.get();
        if("BLOCKED".equals(admin1.getStatus().getName())){
            admin1.setStatus(statusRepository.findByName("PENDING"));
            adminRepository.save(admin1);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user unblocked successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("Admin user unblock failed"));
    }
}

