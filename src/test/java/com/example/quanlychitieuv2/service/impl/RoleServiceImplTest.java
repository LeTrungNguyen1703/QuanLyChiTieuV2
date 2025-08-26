package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.Authentication.RoleRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.RoleResponse;
import com.example.quanlychitieuv2.entity.Role;
import com.example.quanlychitieuv2.mapper.impl.RoleMapper;
import com.example.quanlychitieuv2.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Service xử lý các thao tác liên quan đến Role
 * Quản lý thêm, sửa, xóa và phân quyền cho các vai trò trong hệ thống
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest{
    
    
    @Mock
    RoleRepository roleRepository;
    @Mock
    RoleMapper roleMapper;
    
    @InjectMocks
    RoleServiceImpl roleServiceImpl;
    
    
    private RoleRequest roleRequest;
    private Role role;
    private RoleResponse roleResponse;
    
    @BeforeEach
    void setUp() {
        var permissions = new HashSet<String>();
        permissions.add("1");

        roleRequest = RoleRequest.builder()
                .name("ADMIN")
                .description("Quản trị viên")
                .permissions(permissions)
                .build();
        
        role = Role.builder()
                .name(roleRequest.getName())
                .description(roleRequest.getDescription())
                .permissions(roleRequest.getPermissions())
                .build();
        
        roleResponse = RoleResponse.builder()
                .name(role.getName())
                .description(role.getDescription())
                .permissions(roleRequest.getPermissions())
                .build();
        
    }
    
    @Test
    @DisplayName("Should create role successfully with valid request")
    void Create_ValidRequest_ShouldReturnRoleResponse(){
        
        //Arrange
        when(roleMapper.toEntity(roleRequest)).thenReturn(role);
        when(roleMapper.toRes(role)).thenReturn(roleResponse);
        when(roleRepository.save(role)).thenReturn(role);
        
        //Act
        var result = roleServiceImpl.create(roleRequest);
        
        //Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(roleResponse.getName());
        assertThat(result.getDescription()).isEqualTo(roleResponse.getDescription());
        assertThat(result.getPermissions()).isEqualTo(roleResponse.getPermissions());

        //Verify all mock interactions
        Mockito.verify(roleMapper).toEntity(roleRequest);
        Mockito.verify(roleMapper).toRes(role);
        Mockito.verify(roleRepository).save(role);
        
    }
    
}
