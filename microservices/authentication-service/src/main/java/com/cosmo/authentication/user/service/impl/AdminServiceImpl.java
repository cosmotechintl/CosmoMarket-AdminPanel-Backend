package com.cosmo.authentication.user.service.impl;

import com.cosmo.authentication.core.constant.EmailSubjectConstant;
import com.cosmo.authentication.core.service.MailService;
import com.cosmo.authentication.emailtemplate.entity.AdminEmailLog;
import com.cosmo.authentication.emailtemplate.mapper.AdminEmailLogMapper;
import com.cosmo.authentication.emailtemplate.model.CreateAdminEmailLog;
import com.cosmo.authentication.emailtemplate.model.request.SendEmailRequest;
import com.cosmo.authentication.emailtemplate.repo.AdminEmailLogRepository;
import com.cosmo.authentication.emailtemplate.repo.EmailTemplateRepository;
import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.authentication.user.mapper.AdminMapper;
import com.cosmo.authentication.user.model.AdminUserDetailDto;
import com.cosmo.authentication.user.model.CreateAdminModel;
import com.cosmo.authentication.user.model.FetchAdminDetail;
import com.cosmo.authentication.user.model.SearchAdminUserResponse;
import com.cosmo.authentication.user.model.request.*;
import com.cosmo.authentication.user.repo.AdminRepository;
import com.cosmo.authentication.user.repo.AdminUserSearchRepository;
import com.cosmo.authentication.user.service.AdminService;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final SearchResponse searchResponse;
    private final AdminUserSearchRepository adminUserSearchRepository;
    private final StatusRepository statusRepository;
    private final AdminEmailLogMapper adminEmailLogMapper;
    private final AdminEmailLogRepository adminEmailLogRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Mono<ApiResponse> createAdminUser(CreateAdminModel createAdminModel, CreateAdminEmailLog createAdminEmailLog) {
        Optional<Admin> existedAdminUser = adminRepository.findByUsername(createAdminModel.getEmail());
        Optional<Admin> existedNumber = adminRepository.findByMobileNumber(createAdminModel.getMobileNumber());

        if (existedAdminUser.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("This email is already linked to another account. Please use a different email"));
        }

        if (existedNumber.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("The entered mobile number is already linked to another account. Please use a different number"));
        }

        try {
            Admin admin = adminMapper.mapToEntity(createAdminModel);
            adminRepository.save(admin);

            AdminEmailLog adminEmailLog = adminEmailLogMapper.mapToEntity(createAdminEmailLog, admin);
            adminEmailLogRepository.save(adminEmailLog);

            SendEmailRequest sendEmailRequest = new SendEmailRequest();
            sendEmailRequest.setRecipient(admin.getEmail());
            sendEmailRequest.setSubject(EmailSubjectConstant.EMAIL_VERIFICATION);
            sendEmailRequest.setMessage(adminEmailLog.getMessage());
            mailService.sendEmail(sendEmailRequest);

            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin User Created Successfully"));
        } catch (Exception ex) {
            return Mono.just(ResponseUtil.getFailureResponse("Failed to create admin user: " + ex.getMessage()));
        }
    }

    @Override
    public Mono<ApiResponse<?>> getAllAdminUsers(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Admin, SearchAdminUserResponse> responseBuilder = SearchResponseWithMapperBuilder.<Admin, SearchAdminUserResponse>builder().count(adminUserSearchRepository::count).searchData(adminUserSearchRepository::getAll).mapperFunction(this.adminMapper::getAdminUserResponses).searchParam(searchParam).build();
        PageableResponse<SearchAdminUserResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Admin users fetched successfully"));

    }

    @Override
    public Mono<ApiResponse<?>> getAdminUserDetails(FetchAdminDetail fetchAdminDetail) {
        Optional<Admin> admin = adminRepository.findByEmail(fetchAdminDetail.getEmail());
        if (admin.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin not found"));
        } else {
            AdminUserDetailDto adminUserDetailDto = adminMapper.getAdminUserDetails(admin.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(adminUserDetailDto, "Admin user fetched successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> updateAdminUser(UpdateAdminRequest updateAdminRequest) {
        Optional<Admin> existedNumber = adminRepository.findByMobileNumber(updateAdminRequest.getMobileNumber());
        if (existedNumber.isPresent() && !existedNumber.get().getEmail().equals(updateAdminRequest.getEmail())) {
            return Mono.just(ResponseUtil.getFailureResponse("The mobile number is linked to another account."));
        }
        Optional<Admin> admin = adminRepository.findByEmail(updateAdminRequest.getEmail());
        Admin admin1 = admin.get();
        if ("BLOCKED".equals(admin1.getStatus().getName()) || "DELETED".equals(admin1.getStatus().getName())) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        } else {
            Admin updatedAdmin = adminMapper.updateAdminUser(updateAdminRequest, admin.get());
            adminRepository.save(updatedAdmin);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user updated successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> deleteAdminUser(DeleteAdminRequest deleteAdminRequest) {
        Optional<Admin> admin = adminRepository.findByEmail(deleteAdminRequest.getEmail());
        if (admin.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        } else {
            Admin admin1 = admin.get();
            if ("BLOCKED".equals(admin1.getStatus().getName()) || "DELETED".equals(admin1.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
            }
            admin1.setStatus(statusRepository.findByName("DELETED"));
            admin1.setActive(false);
            adminRepository.save(admin1);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user deleted successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> blockAdminUser(BlockAdminRequest blockAdminRequest) {
        Optional<Admin> admin = adminRepository.findByEmail(blockAdminRequest.getEmail());
        if (admin.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        } else {
            Admin admin1 = admin.get();
            if ("DELETED".equals(admin1.getStatus().getName()) || "BLOCKED".equals(admin1.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
            } else {
                admin1.setStatus(statusRepository.findByName("BLOCKED"));
                admin1.setActive(false);
                adminRepository.save(admin1);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user blocked successfully"));
            }
        }
    }

    @Override
    public Mono<ApiResponse<?>> unblockAdminUser(UnblockAdminUserRequest unblockAdminRequest) {
        Optional<Admin> admin = adminRepository.findByEmail(unblockAdminRequest.getEmail());
        Admin admin1 = admin.get();
        if ("BLOCKED".equals(admin1.getStatus().getName())) {
            admin1.setStatus(statusRepository.findByName("PENDING"));
            admin1.setActive(true);
            adminRepository.save(admin1);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user unblocked successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("Admin user unblock failed"));
    }

    @Override
    public Mono<ApiResponse<?>> createAdminPassword(CreatePasswordRequest createPasswordRequest) {
        Optional<AdminEmailLog> adminEmailLog = adminEmailLogRepository.findByUuid(createPasswordRequest.getUuid());
        if (adminEmailLog.isEmpty()) {
            return Mono.just(ResponseUtil.getFailureResponse("Invalid UUID"));
        } else {
            AdminEmailLog existingAdminEmailLog = adminEmailLog.get();
            if (existingAdminEmailLog.isExpired()) {
                return Mono.just(ResponseUtil.getFailureResponse("Link expired"));
            } else {
                Optional<Admin> admin = adminRepository.findByEmail(existingAdminEmailLog.getAdmin().getEmail());
                Admin existingAdmin = admin.get();
                existingAdmin.setPassword(passwordEncoder.encode(createPasswordRequest.getPassword()));
                existingAdmin.setStatus(statusRepository.findByName("ACTIVE"));
                existingAdmin.setPasswordChangeDate(new Date());
                existingAdmin.setActive(true);
                adminRepository.save(existingAdmin);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Password created successfully"));

            }
        }
    }
}

