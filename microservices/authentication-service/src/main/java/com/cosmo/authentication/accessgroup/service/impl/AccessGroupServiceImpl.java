package com.cosmo.authentication.accessgroup.service.impl;

import com.cosmo.authentication.accessgroup.mapper.AccessGroupMapper;
import com.cosmo.authentication.accessgroup.mapper.AccessGroupRoleMapMapper;
import com.cosmo.authentication.accessgroup.model.CreateAccessGroupModel;
import com.cosmo.authentication.accessgroup.model.SearchAccessGroupResponse;
import com.cosmo.authentication.accessgroup.model.request.DeleteAccessGroupRequest;
import com.cosmo.authentication.accessgroup.model.request.FetchAccessGroupDetail;
import com.cosmo.authentication.accessgroup.model.request.UpdateAccessGroupRequest;
import com.cosmo.authentication.accessgroup.service.AccessGroupService;
import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.accessgroup.model.AccessGroupDetailDto;
import com.cosmo.authentication.accessgroup.repo.AccessGroupRepository;
import com.cosmo.authentication.accessgroup.repo.AccessGroupSearchRepository;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AccessGroupServiceImpl implements AccessGroupService {
    private final AccessGroupRepository accessGroupRepository;
    private final AccessGroupMapper accessGroupMapper;
    private final AccessGroupRoleMapMapper accessGroupRoleMapMapper;
    private final SearchResponse searchResponse;
    private final AccessGroupSearchRepository accessGroupSearchRepository;
    private final StatusRepository statusRepository;


    @Override
    @Transactional
    public Mono<ApiResponse> createAccessGroup(CreateAccessGroupModel createAccessGroupModel) {
        AccessGroup accessGroup = accessGroupMapper.toEntity(createAccessGroupModel);
        accessGroupRoleMapMapper.createAccessGroupRoleMap(accessGroup, createAccessGroupModel.getRoles());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group created"));
    }

    @Override
    @Transactional
    public Mono<ApiResponse<?>> updateAccessGroup(UpdateAccessGroupRequest request) {
        Optional<AccessGroup> existedAccessGroup = accessGroupRepository.findById(request.getId());

        if (existedAccessGroup.isPresent()){
            AccessGroup updatedAccessGroup= accessGroupMapper.updateAccessGroup(request,existedAccessGroup.get());
            accessGroupRoleMapMapper.updateAccessGroupRoleMap(updatedAccessGroup,request.getRoles());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group updated successfully"));
            }
        else {
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group not found"));
    }}

    @Override
    public Mono<ApiResponse<?>> deleteAccessGroup(DeleteAccessGroupRequest deleteAccessGroupRequest) {
        Optional<AccessGroup> accessGroup= accessGroupRepository.findById(deleteAccessGroupRequest.getId());
        if (accessGroup.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group not found"));
        }
        else{
            AccessGroup accessGroup1= accessGroup.get();
            if ("DELETED".equals(accessGroup1.getStatus().getName())){
                return Mono.just(ResponseUtil.getNotFoundResponse("Access group not found"));
            }
            accessGroup1.setStatus(statusRepository.findByName("DELETED"));
            accessGroupRepository.save(accessGroup1);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group deleted successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> getAllAccessGroup(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<AccessGroup, SearchAccessGroupResponse> responseBuilder = SearchResponseWithMapperBuilder
                .<AccessGroup, SearchAccessGroupResponse>builder()
                .count(accessGroupSearchRepository::count)
                .searchData(accessGroupSearchRepository::getAll)
                .mapperFunction(this.accessGroupMapper::getAccessGroupResponses)
                .searchParam(searchParam)
                .build();
        PageableResponse<SearchAccessGroupResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Access groups fetched successfully."));
    }

    @Override
    public Mono<ApiResponse<?>> getAccessGroupDetail(FetchAccessGroupDetail fetchAccessGroupDetail) {
        Optional<AccessGroup> accessGroup = accessGroupRepository.findByName(fetchAccessGroupDetail.getName());
        if (accessGroup.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group  not found"));
        } else {
            AccessGroupDetailDto accessGroupDetailDto = accessGroupMapper.getAccessGroupDetailDto(accessGroup.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(accessGroupDetailDto, "Access group fetched successfully"));
        }
    }


}
