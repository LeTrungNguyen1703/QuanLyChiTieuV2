package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.ChucNangRequest;
import com.example.quanlychitieuv2.dto.response.ChucNangResponse;
import com.example.quanlychitieuv2.entity.ChucNang;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.ChucNangServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chuc-nang")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChucNangController extends AbstractBaseController<ChucNangRequest, ChucNangResponse, ChucNang, Integer> {

    ChucNangServiceImpl chucNangService;

    @Override
    protected AbstractBaseService<ChucNangRequest, ChucNangResponse, ChucNang, Integer> abstractService() {
        return chucNangService;
    }
}
