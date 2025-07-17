package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.LoaiKhoanChi.LoaiKhoanChiDto;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanChiResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanChi;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.LoaiKhoanChiSerViceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loai-khoan-chi")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiKhoanChiController extends AbstractBaseController<LoaiKhoanChiDto, LoaiKhoanChiResponse, LoaiKhoanChi, Integer> {

    LoaiKhoanChiSerViceImpl loaiKhoanChiSerViceImpl;

    @Override
    protected AbstractBaseService<LoaiKhoanChiDto, LoaiKhoanChiResponse, LoaiKhoanChi, Integer> abstractService() {
        return loaiKhoanChiSerViceImpl;
    }
}
