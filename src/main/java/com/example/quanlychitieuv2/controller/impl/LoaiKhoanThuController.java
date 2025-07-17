package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.LoaiKhoanThu.LoaiKhoanThuDto;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanThuResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanThu;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.LoaiKhoanThuServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loai-khoan-thu")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiKhoanThuController extends AbstractBaseController<LoaiKhoanThuDto, LoaiKhoanThuResponse, LoaiKhoanThu,Integer> {

    LoaiKhoanThuServiceImpl loaiKhoanThuServiceImpl;


    @Override
    protected AbstractBaseService<LoaiKhoanThuDto, LoaiKhoanThuResponse, LoaiKhoanThu, Integer> abstractService() {
        return loaiKhoanThuServiceImpl;
    }
}
