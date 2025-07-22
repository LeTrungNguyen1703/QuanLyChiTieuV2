package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.Authentication.RoleRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.RoleResponse;
import com.example.quanlychitieuv2.entity.Permission;
import com.example.quanlychitieuv2.entity.Role;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.RoleMapper;
import com.example.quanlychitieuv2.repository.PermissionRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl extends AbstractBaseService<RoleRequest, RoleResponse, Role, String> {

    RoleMapper roleMapper;
    FindBy findBy;
    PermissionRepository permissionRepository;

    public RoleServiceImpl(JpaRepository<Role, String> jpaRepository, RoleMapper roleMapper, PermissionRepository permissionRepository, FindBy findBy) {
        super(jpaRepository);
        this.roleMapper = roleMapper;
        this.permissionRepository = permissionRepository;
        this.findBy = findBy;
    }

    @Override
    protected BaseMapper<RoleRequest, RoleResponse, Role> getMapper() {
        return roleMapper;
    }

    @Override
    public RoleResponse create(RoleRequest roleRequest) {
        log.info("Creating role with request: {}", roleRequest);
        Role role = roleMapper.toEntity(roleRequest);
        List<Permission> permissions = permissionRepository.findAllById(roleRequest.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = jpaRepository.save(role);
        log.info("Role created successfully: {}", role);

        return roleMapper.toRes(role);
    }

    @Override
    public void update(String s, RoleRequest roleRequest) {
        log.info("Updating role with request: {}", roleRequest);
        Role exitsRole = findBy.findRoleById(s);

        if (roleRequest.getPermissions() != null) {
            List<Permission> permissions = permissionRepository.findAllById(roleRequest.getPermissions());
            exitsRole.setPermissions(new HashSet<>(permissions));
            jpaRepository.save(exitsRole);
            log.info("Permissions updated for role: {}", exitsRole.getName());
        }

        roleMapper.updateEntity(roleRequest, exitsRole);
    }
}
