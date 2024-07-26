package com.cosmo.authentication.loggedinuser.mapper;

import com.cosmo.authentication.loggedinuser.model.LoggedInUserDetailDto;
import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.repo.AdminRepository;
import com.cosmo.common.mapper.StatusMapper;
import com.cosmo.common.repository.StatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class LoggedInUserMapper {
    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected StatusMapper statusMapper;
    @Autowired
    protected AdminRepository adminRepository;

    public abstract LoggedInUserDetailDto getLoggedInUserDetailDto(Admin admin);

}
