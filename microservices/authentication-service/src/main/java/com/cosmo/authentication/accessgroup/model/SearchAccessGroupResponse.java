package com.cosmo.authentication.accessgroup.model;

import com.cosmo.common.model.ModelBase;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchAccessGroupResponse extends ModelBase {
    private String name;
    private TypeDto type;
}
