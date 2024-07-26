package com.cosmo.authentication.emailtemplate.mapper;

import com.cosmo.authentication.core.constant.EmailTemplateConstant;
import com.cosmo.authentication.core.constant.FreeMarkerTemplateConstant;
import com.cosmo.authentication.core.model.PrepareEmailContentDto;
import com.cosmo.authentication.emailtemplate.entity.AdminEmailLog;
import com.cosmo.authentication.emailtemplate.entity.EmailTemplate;
import com.cosmo.authentication.emailtemplate.repo.AdminEmailLogRepository;
import com.cosmo.authentication.emailtemplate.repo.EmailTemplateRepository;
import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.repo.AdminRepository;
import com.cosmo.authentication.util.EmailContentUtil;
import com.cosmo.authentication.util.ExpirationTimeUtil;
import com.cosmo.authentication.util.UuidUtil;
import freemarker.template.Template;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.time.Year;
import java.util.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AdminEmailLogMapper {
    @Autowired
    protected AdminEmailLogRepository adminEmailLogRepository;
    @Autowired
    protected AdminRepository adminRepository;
    @Autowired
    private freemarker.template.Configuration freeMarkerConfig;
    @Autowired
    protected EmailTemplateRepository emailTemplateRepository;
    @Autowired
    private EmailContentUtil emailContentUtil;

    public AdminEmailLog mapToEntity(Admin admin) {
        Date expirationTime = ExpirationTimeUtil.calculateExpirationTime();
        String uuid = UuidUtil.generateUuid();

        PrepareEmailContentDto prepareEmailContentDto = new PrepareEmailContentDto();
        prepareEmailContentDto.setAdminUserName(admin.getName());
        prepareEmailContentDto.setTemplateName(EmailTemplateConstant.ADMIN_MAIL_VERIFICATION);
        prepareEmailContentDto.setUuid(uuid);
        prepareEmailContentDto.setExpirationTime(expirationTime);
        String emailContent = emailContentUtil.prepareEmailContent(prepareEmailContentDto);

        AdminEmailLog adminEmailLog = new AdminEmailLog();
        adminEmailLog.setEmail(admin.getEmail());
        adminEmailLog.setAdmin(admin);
        adminEmailLog.setMessage(emailContent);
        adminEmailLog.setUuid(uuid);
        adminEmailLog.setExpired(false);
        adminEmailLogRepository.save(adminEmailLog);
        return adminEmailLog;
    }
}
