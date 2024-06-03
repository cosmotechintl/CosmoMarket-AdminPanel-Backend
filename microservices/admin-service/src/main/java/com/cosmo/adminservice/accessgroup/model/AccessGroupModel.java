package com.cosmo.adminservice.accessgroup.model;

import com.cosmo.common.entity.Status;
import lombok.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccessGroupModel {
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Long statusId;
    private boolean isSuperAdminGroup;
    private String remarks;
    private List<Long> roleId;

    public Status getStatus(){
        Status status = new Status();
        status.setId(statusId);
        return status;
    }
}
