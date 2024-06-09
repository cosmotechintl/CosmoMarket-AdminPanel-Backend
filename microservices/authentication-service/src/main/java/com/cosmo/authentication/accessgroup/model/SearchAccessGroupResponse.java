package com.cosmo.authentication.accessgroup.model;

import com.cosmo.common.model.ModelBase;
import com.cosmo.common.model.StatusDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchAccessGroupResponse extends ModelBase {
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private StatusDto status;
    private Boolean isSuperAdminGroup;
    private String remarks;

}
