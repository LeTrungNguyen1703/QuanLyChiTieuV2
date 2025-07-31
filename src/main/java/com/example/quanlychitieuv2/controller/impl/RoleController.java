package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.Authentication.RoleRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.RoleResponse;
import com.example.quanlychitieuv2.entity.Role;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.RoleServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController extends AbstractBaseController<RoleRequest, RoleResponse, Role, String> {

   RoleServiceImpl roleServiceImpl;

    @Override
    protected AbstractBaseService<RoleRequest, RoleResponse, Role, String> abstractService() {
        return roleServiceImpl;
    }
}
