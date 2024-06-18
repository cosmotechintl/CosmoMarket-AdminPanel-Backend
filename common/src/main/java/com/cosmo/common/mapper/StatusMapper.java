package com.cosmo.common.mapper;

import com.cosmo.common.entity.Status;
import com.cosmo.common.model.StatusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StatusMapper {

   public abstract StatusDto entityToStatusDto(Status status);

}
