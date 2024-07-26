package com.cosmo.authentication.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserRequest {
    private Long id;
    private String name;
    private String password;
    private String username;
    private boolean isActive;
    private String email;
    private String mobileNumber;
    private String address;
    private Long statusId;
    private Long accessGroupId;
    private Date passwordChangeDate;
    private Date lastLoggedInTime;
    private Integer wrongPasswordAttemptCount;
    private String profilePictureName;
    private String otpAuthSecret;
    private boolean twoFactorEnabled;
    private Integer wrongOtpAuthAttemptCount;
    private boolean isSuperAdmin;
}
