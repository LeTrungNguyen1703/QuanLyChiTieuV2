package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.Authentication.PermissionRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.PermissionResponse;
import com.example.quanlychitieuv2.entity.Permission;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.PermissionMapper;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissonServiceImpl extends AbstractBaseService<PermissionRequest, PermissionResponse, Permission, String> {

    PermissionMapper permissionMapper;

    @Autowired
    public PermissonServiceImpl(JpaRepository<Permission, String> jpaRepository, PermissionMapper permissionMapper) {
        super(jpaRepository);
        this.permissionMapper = permissionMapper;
    }


    @Override
    protected BaseMapper<PermissionRequest, PermissionResponse, Permission> getMapper() {
        return permissionMapper;
    }
}
