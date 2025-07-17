package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.Authentication.PermissionRequest;
import com.example.quanlychitieuv2.dto.request.Authentication.RoleRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.PermissionResponse;
import com.example.quanlychitieuv2.dto.response.Authentication.RoleResponse;
import com.example.quanlychitieuv2.entity.Permission;
import com.example.quanlychitieuv2.entity.Role;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper extends BaseMapper<RoleRequest, RoleResponse, Role> {

}
