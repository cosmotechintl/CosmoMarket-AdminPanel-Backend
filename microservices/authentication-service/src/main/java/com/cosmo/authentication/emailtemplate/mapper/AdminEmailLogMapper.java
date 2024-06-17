package com.cosmo.authentication.emailtemplate.mapper;

import com.cosmo.authentication.emailtemplate.entity.AdminEmailLog;
import com.cosmo.authentication.emailtemplate.model.CreateAdminEmailLog;
import com.cosmo.authentication.emailtemplate.repo.AdminEmailLogRepository;
import com.cosmo.authentication.user.repo.AdminRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AdminEmailLogMapper {
    @Autowired
    protected AdminEmailLogRepository adminEmailLogRepository;
    @Autowired
    protected AdminRepository adminRepository;

    public AdminEmailLog mapToEntity(CreateAdminEmailLog createAdminEmailLog){
        AdminEmailLog adminEmailLog = new AdminEmailLog();
        adminEmailLog.setExpired(false);
        return adminEmailLog;
    }
}
