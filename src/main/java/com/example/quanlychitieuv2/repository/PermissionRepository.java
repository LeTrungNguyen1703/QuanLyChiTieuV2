package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}