package com.cosmo.authentication.accessgroup.service;

import com.cosmo.authentication.accessgroup.model.CreateAccessGroupModel;
import com.cosmo.authentication.accessgroup.model.request.FetchAccessGroupDetail;
import com.cosmo.authentication.accessgroup.model.request.UpdateAccessGroupRequest;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import reactor.core.publisher.Mono;

public interface AccessGroupService {
        Mono<ApiResponse> createAccessGroup(CreateAccessGroupModel createAccessGroupModel);
        Mono<ApiResponse<?>> updateAccessGroup(UpdateAccessGroupRequest request);
        void deleteAccessGroup(Long id);
        Mono<ApiResponse<?>> getAllAccessGroup(SearchParam searchParam);
        Mono<ApiResponse<?>> getAccessGroupDetail(FetchAccessGroupDetail fetchAccessGroupDetail);
}
