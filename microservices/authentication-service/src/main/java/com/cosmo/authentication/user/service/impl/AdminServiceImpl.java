package com.cosmo.authentication.user.service.impl;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.mapper.AdminMapper;
import com.cosmo.authentication.user.model.AdminUserDetailDto;
import com.cosmo.authentication.user.model.CreateAdminModel;
import com.cosmo.authentication.user.model.FetchAdminDetail;
import com.cosmo.authentication.user.model.SearchAdminUserResponse;
import com.cosmo.authentication.user.repo.AdminRepository;
import com.cosmo.authentication.user.repo.AdminUserSearchRepository;
import com.cosmo.authentication.user.service.AdminService;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final SearchResponse searchResponse;
    private final AdminUserSearchRepository adminUserSearchRepository;

    @Override
    @Transactional
    public Mono<ApiResponse> createAdminUser(CreateAdminModel createAdminModel) {
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
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response,"Admin users fetched successfully. "));

    }

    @Override
    public Mono<ApiResponse<?>> getAdminUserDetails(FetchAdminDetail fetchAdminDetail) {
        Optional<Admin> admin = adminRepository.findByUsername(fetchAdminDetail.getUsername());
        if (admin.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("admin not found"));
        }
        else {
            AdminUserDetailDto adminUserDetailDto = adminMapper.getAdminUserDetailDto(admin.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(adminUserDetailDto,"admin user fetched successfully"));
        }
    }
}

