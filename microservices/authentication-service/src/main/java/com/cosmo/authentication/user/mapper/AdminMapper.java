package com.cosmo.authentication.user.mapper;

import com.cosmo.authentication.accessgroup.repo.AccessGroupRepository;
import com.cosmo.authentication.emailtemplate.repo.AdminEmailLogRepository;
import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.model.AdminUserDetailDto;
import com.cosmo.authentication.user.model.CreateAdminModel;
import com.cosmo.authentication.user.model.SearchAdminUserResponse;
import com.cosmo.authentication.user.model.request.UpdateAdminRequest;
import com.cosmo.authentication.user.repo.AdminRepository;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.repository.StatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AdminMapper {

    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected AccessGroupRepository accessGroupRepository;
    @Autowired
    protected AdminRepository adminRepository;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected AdminEmailLogRepository adminEmailLogRepository;
   public Admin mapToEntity(CreateAdminModel createAdminModel){
       Admin admin= new Admin();

       admin.setName(createAdminModel.getName());
       admin.setMobileNumber(createAdminModel.getMobileNumber());
       admin.setAddress(createAdminModel.getAddress());
       admin.setEmail(createAdminModel.getEmail());
       admin.setAccessGroup(accessGroupRepository.findByName(createAdminModel.getAccessGroup().getName()).orElseThrow(
               ()-> new ResourceNotFoundException("access group not found")
       ));
       admin.setUsername(createAdminModel.getEmail());
       admin.setActive(false);
       admin.setStatus(statusRepository.findByName(StatusConstant.PENDING.getName()));
       admin.setSuperAdmin(false);
       return admin;
   }
   public Admin updateAdminUser(UpdateAdminRequest request, Admin admin){
       admin.setName(request.getName());
       admin.setMobileNumber(request.getMobileNumber());
       admin.setAddress(request.getAddress());
       admin.setAccessGroup(accessGroupRepository.findByName(request.getAccessGroup().getName()).orElseThrow(
               ()-> new ResourceNotFoundException("access group not found")
       ));
       return admin;
   }

   public abstract SearchAdminUserResponse entityToResponse(Admin admin);

   public List<SearchAdminUserResponse> getAdminUserResponses(List<Admin> admins){
       return admins.stream().map(this::entityToResponse).collect(Collectors.toList());
   }
    public abstract AdminUserDetailDto getAdminUserDetails(Admin admin);

}
