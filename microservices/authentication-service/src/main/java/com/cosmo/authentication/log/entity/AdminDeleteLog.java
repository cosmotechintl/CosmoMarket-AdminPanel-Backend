package com.cosmo.authentication.log.entity;

import com.cosmo.authentication.user.entity.Admin;
import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "admin_delete_log")
public class AdminDeleteLog extends AbstractEntity {
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="admin", nullable=false, referencedColumnName = "id")
    private Admin admin;

    @Column(name = "deleted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;
}
