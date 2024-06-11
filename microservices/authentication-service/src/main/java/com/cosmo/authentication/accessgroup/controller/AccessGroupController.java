package com.cosmo.authentication.accessgroup.controller;

import com.cosmo.authentication.accessgroup.entity.AccessGroup;
import com.cosmo.authentication.accessgroup.model.CreateAccessGroupModel;
import com.cosmo.authentication.accessgroup.model.request.FetchAccessGroupDetail;
import com.cosmo.authentication.accessgroup.model.request.UpdateAccessGroupRequest;
import com.cosmo.authentication.accessgroup.service.AccessGroupService;
import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.ACCESS_GROUP)
@RequiredArgsConstructor
public class AccessGroupController {
    private final AccessGroupService accessGroupService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> addAccessGroup(@RequestBody @Valid CreateAccessGroupModel createAccessGroupModel) {
        return accessGroupService.createAccessGroup(createAccessGroupModel);
    }

    @GetMapping()
    public Mono<ApiResponse<?>> getAllAccessGroups(@RequestBody @Valid SearchParam searchParam) {
        return accessGroupService.getAllAccessGroup(searchParam);
    }

    @GetMapping(ApiConstant.GET + ApiConstant.SLASH + ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getAccessGroupDetail(@RequestBody @Valid FetchAccessGroupDetail fetchAccessGroupDetail) {
        return accessGroupService.getAccessGroupDetail(fetchAccessGroupDetail);
    }
    @PostMapping(ApiConstant.EDIT)
    public Mono<ApiResponse<?>> editAccessGroup(@RequestBody @Valid UpdateAccessGroupRequest request){
        return accessGroupService.updateAccessGroup(request);

    }
}
