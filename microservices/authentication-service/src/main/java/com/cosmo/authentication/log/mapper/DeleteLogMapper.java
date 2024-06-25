package com.cosmo.authentication.log.mapper;

import com.cosmo.authentication.log.entity.AdminDeleteLog;
import com.cosmo.authentication.log.model.AdminDeleteLogModel;
import com.cosmo.common.model.ModelBase;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class DeleteLogMapper extends ModelBase {
    public AdminDeleteLog mapToEntity(AdminDeleteLogModel adminDeleteLogModel) {
        AdminDeleteLog adminDeleteLog = new AdminDeleteLog();
        adminDeleteLog.setRemarks(adminDeleteLogModel.getRemarks());
        adminDeleteLog.setAdmin(adminDeleteLogModel.getAdmin());
        adminDeleteLog.setDeletedDate(new Date());
        return adminDeleteLog;
    }
}
