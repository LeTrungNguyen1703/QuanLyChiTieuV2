package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.LoaiKhoanChi.LoaiKhoanChiDto;
import com.example.quanlychitieuv2.dto.request.UserRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.RoleResponse;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanChiResponse;
import com.example.quanlychitieuv2.dto.response.UserResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanChi;
import com.example.quanlychitieuv2.entity.Role;
import com.example.quanlychitieuv2.entity.User;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends BaseMapper<UserRequest, UserResponse, User> {

    @Override
    User toEntity(UserRequest userRequest);

    @Override
    UserResponse toRes(User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Override
    User updateEntity(UserRequest userRequest, @MappingTarget User entity);


    @Named("toRoleResponseList")
    static List<String> toRoleResponseList(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
