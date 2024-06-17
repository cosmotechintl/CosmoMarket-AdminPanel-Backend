package com.cosmo.authentication.emailtemplate.entity;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_email_log")
public class AdminEmailLog extends AbstractEntity {

    @Column(name="email",nullable = false)
    private String email;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="admin", nullable=false, referencedColumnName = "id")
    private Admin admin;

    @Column(name="message",nullable = false)
    private String message;

    @Column(name="is_sent")
    private boolean isSent;

    @Column(name="is_expired")
    private boolean isExpired;

    @Column(name="uuid")
    private String uuid;
}
