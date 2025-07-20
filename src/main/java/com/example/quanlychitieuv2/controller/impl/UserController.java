package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.LoaiKhoanChi.LoaiKhoanChiDto;
import com.example.quanlychitieuv2.dto.request.UserRequest;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanChiResponse;
import com.example.quanlychitieuv2.dto.response.UserResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanChi;
import com.example.quanlychitieuv2.entity.User;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.LoaiKhoanChiSerViceImpl;
import com.example.quanlychitieuv2.service.impl.UserSeviceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController extends AbstractBaseController<UserRequest, UserResponse, User, Integer> {

    UserSeviceImpl userSeviceImpl;

    @Override
    protected AbstractBaseService<UserRequest, UserResponse, User, Integer> abstractService() {
        return userSeviceImpl;
    }
}
