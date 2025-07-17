package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.Authentication.PermissionRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.PermissionResponse;
import com.example.quanlychitieuv2.entity.Permission;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionMapper extends BaseMapper<PermissionRequest, PermissionResponse, Permission> {

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Permission updateEntity(PermissionRequest permissionRequest, @MappingTarget Permission entity);
}
