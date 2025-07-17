package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.Authentication.PermissionRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.PermissionResponse;
import com.example.quanlychitieuv2.entity.Permission;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.PermissonServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissonController extends AbstractBaseController<PermissionRequest, PermissionResponse, Permission, String> {

    PermissonServiceImpl permissonServiceImpl;

    @Override
    protected AbstractBaseService<PermissionRequest, PermissionResponse, Permission, String> abstractService() {
        return permissonServiceImpl;
    }
}
