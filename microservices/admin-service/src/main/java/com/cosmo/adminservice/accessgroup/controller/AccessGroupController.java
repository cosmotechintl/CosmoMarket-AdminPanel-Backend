package com.cosmo.adminservice.accessgroup.controller;

import com.cosmo.adminservice.accessgroup.model.AccessGroupModel;
import com.cosmo.adminservice.accessgroup.model.AccessGroupResponse;
import com.cosmo.adminservice.accessgroup.service.AccessGroupService;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.common.constant.ApiConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.ACCESS_GROUP)
@RequiredArgsConstructor
public class AccessGroupController {
    private final AccessGroupService accessGroupService;

    @PostMapping()
    public ResponseEntity<AccessGroupResponse> addAccessGroup(@RequestBody AccessGroupModel accessGroupModel){

        return ResponseEntity.ok(accessGroupService.createAccessGroup(accessGroupModel));

    }
    @GetMapping()
    public ResponseEntity<List<AccessGroup>> getAllAccessGroup(){
        List<AccessGroup> accessGroups= accessGroupService.getAllAccessGroup();
        return new ResponseEntity<>(accessGroups, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccessGroup> getAccessGroup(@PathVariable long id){
        AccessGroup accessGroup= accessGroupService.getAccessgroup(id);
        if(accessGroup!= null){
            return new ResponseEntity<>(accessGroup,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
