package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.Authentication.RoleRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.RoleResponse;
import com.example.quanlychitieuv2.entity.Role;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.RoleMapper;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Service xử lý các thao tác liên quan đến Role
 * Quản lý thêm, sửa, xóa và phân quyền cho các vai trò trong hệ thống
 */
@Service
@Slf4j
public class RoleServiceImpl extends AbstractBaseService<RoleRequest, RoleResponse, Role, String> {

    RoleMapper roleMapper;
    FindBy findBy;

    public RoleServiceImpl(JpaRepository<Role, String> jpaRepository, RoleMapper roleMapper, FindBy findBy) {
        super(jpaRepository);
        this.roleMapper = roleMapper;
        this.findBy = findBy;
    }

    @Override
    protected BaseMapper<RoleRequest, RoleResponse, Role> getMapper() {
        return roleMapper;
    }

    /**
     * Tạo một vai trò mới với các quyền được chỉ định
     *
     * @param roleRequest thông tin vai trò cần tạo
     * @return đối tượng RoleResponse chứa thông tin vai trò đã tạo
     */
    @Override
    public RoleResponse create(RoleRequest roleRequest) {
        log.info("Creating role with request: {}", roleRequest);
        Role role = roleMapper.toEntity(roleRequest);

        // Thiết lập danh sách quyền trực tiếp từ request
        if (roleRequest.getPermissions() != null) {
            role.setPermissions(roleRequest.getPermissions());
        }

        role = jpaRepository.save(role);
        log.info("Role created successfully: {}", role);

        return roleMapper.toRes(role);
    }

    /**
     * Cập nhật thông tin và quyền của một vai trò
     *
     * @param s            ID của vai trò cần cập nhật
     * @param roleRequest thông tin vai trò mới
     */
    @Override
    public void update(String s, RoleRequest roleRequest) {
        log.info("Updating role with request: {}", roleRequest);
        Role exitsRole = findBy.findRoleById(s);

        if (roleRequest.getPermissions() != null) {
            exitsRole.setPermissions(roleRequest.getPermissions());
            jpaRepository.save(exitsRole);
            log.info("Permissions updated for role: {}", exitsRole.getName());
        }

        roleMapper.updateEntity(roleRequest, exitsRole);
    }
}
