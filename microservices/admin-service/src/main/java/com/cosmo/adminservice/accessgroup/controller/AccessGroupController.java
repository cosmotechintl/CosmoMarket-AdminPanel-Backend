package com.cosmo.adminservice.accessgroup.controller;

import com.cosmo.adminservice.accessgroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessgroup.model.AccessGroupResponse;
import com.cosmo.adminservice.accessgroup.model.request.NameRequest;
import com.cosmo.adminservice.accessgroup.service.AccessGroupService;
import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.ACCESS_GROUP)
@RequiredArgsConstructor
public class AccessGroupController {
    private final AccessGroupService accessGroupService;

    @PostMapping(ApiConstant.CREATE)
    public ResponseEntity<AccessGroupResponse> addAccessGroup(@RequestBody AccessGroupModel accessGroupModel){

        return ResponseEntity.ok(accessGroupService.createAccessGroup(accessGroupModel));

    }

    @GetMapping()
    public ResponseEntity<ApiResponse<?>> getAllAccessGroups(@RequestBody SearchParam searchParam){

        return ResponseEntity.ok().body(accessGroupService.getAllAccessGroup(searchParam));
    }

    @GetMapping(ApiConstant.GET)
    public ResponseEntity<ApiResponse<?>> getAccessGroupByName(@RequestBody NameRequest nameRequest){
        String name = nameRequest.getName();
        return ResponseEntity.ok(accessGroupService.getAccessGroupByName(name));
    }
}
