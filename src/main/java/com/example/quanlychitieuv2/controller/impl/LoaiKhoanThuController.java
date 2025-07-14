package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.LoaiKhoanThuDto;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanThuResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanThu;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.LoaiKhoanChiSerViceImpl;
import com.example.quanlychitieuv2.service.impl.LoaiKhoanThuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loai-khoan-thu")
public class LoaiKhoanThuController extends AbstractBaseController<LoaiKhoanThuDto, LoaiKhoanThuResponse, LoaiKhoanThu,Integer> {

    LoaiKhoanThuServiceImpl loaiKhoanThuServiceImpl;

    @Autowired
    public LoaiKhoanThuController (LoaiKhoanThuServiceImpl loaiKhoanThuServiceImpl) {
        this.loaiKhoanThuServiceImpl = loaiKhoanThuServiceImpl;
    }

    @Override
    protected AbstractBaseService<LoaiKhoanThuDto, LoaiKhoanThuResponse, LoaiKhoanThu, Integer> abstractService() {
        return loaiKhoanThuServiceImpl;
    }
}
