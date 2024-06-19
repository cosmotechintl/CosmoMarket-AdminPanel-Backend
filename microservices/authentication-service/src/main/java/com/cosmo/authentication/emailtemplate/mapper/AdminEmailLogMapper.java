package com.cosmo.authentication.emailtemplate.mapper;

import com.cosmo.authentication.core.constant.EmailTemplateConstant;
import com.cosmo.authentication.core.constant.FreeMarkerTemplateConstant;
import com.cosmo.authentication.emailtemplate.entity.AdminEmailLog;
import com.cosmo.authentication.emailtemplate.entity.EmailTemplate;
import com.cosmo.authentication.emailtemplate.model.CreateAdminEmailLog;
import com.cosmo.authentication.emailtemplate.repo.AdminEmailLogRepository;
import com.cosmo.authentication.emailtemplate.repo.EmailTemplateRepository;
import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.repo.AdminRepository;
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
    public AdminEmailLog mapToEntity(CreateAdminEmailLog createAdminEmailLog, Admin admin){
        // Calculate expiration time 24 hours from now
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        Date expirationTime = calendar.getTime();

        //Generate UUID
        String uuid = UUID.randomUUID().toString();

        // Prepare email content from template
        EmailTemplate emailTemplate = emailTemplateRepository.findEmailTemplateByName(EmailTemplateConstant.ADMIN_MAIL_VERIFICATION);
        Map<String, Object> model = new HashMap<>();
        model.put(FreeMarkerTemplateConstant.USERNAME, admin.getName());
        model.put(FreeMarkerTemplateConstant.VERIFICATION_LINK, "http://localhost:3000/create-password/"+uuid); // Replace with actual verification link
        model.put(FreeMarkerTemplateConstant.EXPIRATION_TIME, expirationTime);
        model.put(FreeMarkerTemplateConstant.CURRENT_YEAR, Year.now().getValue());

        String emailContent;
        try {
            Template template = new Template("emailTemplate", emailTemplate.getTemplate(), freeMarkerConfig);
            emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            // Rollback transaction if there's an error processing the template
            throw new RuntimeException("Error processing email template: "+ e.getMessage());
        }
        AdminEmailLog adminEmailLog = new AdminEmailLog();
        adminEmailLog.setEmail(admin.getEmail());
        adminEmailLog.setAdmin(admin);
        adminEmailLog.setMessage(emailContent);
        adminEmailLog.setSent(true); //don't make it true here
        adminEmailLog.setUuid(uuid);
        adminEmailLog.setExpired(false);
        return adminEmailLog;
    }
}
