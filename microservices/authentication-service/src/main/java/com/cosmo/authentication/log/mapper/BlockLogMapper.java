package com.cosmo.authentication.log.mapper;

import com.cosmo.authentication.log.entity.AdminBlockLog;
import com.cosmo.authentication.log.entity.AdminDeleteLog;
import com.cosmo.authentication.log.model.AdminBlockLogModel;
import com.cosmo.authentication.log.model.AdminDeleteLogModel;
import com.cosmo.common.model.ModelBase;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BlockLogMapper {
    public AdminBlockLog mapToEntity(AdminBlockLogModel adminBlockLogModel) {
        AdminBlockLog adminBlockLog = new AdminBlockLog();
        adminBlockLog.setRemarks(adminBlockLogModel.getRemarks());
        adminBlockLog.setAdmin(adminBlockLogModel.getAdmin());
        adminBlockLog.setBlockedDate(new Date());
        return adminBlockLog;
    }
}
