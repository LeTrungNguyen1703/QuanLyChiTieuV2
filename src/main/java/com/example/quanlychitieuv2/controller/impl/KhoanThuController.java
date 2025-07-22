package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.request.LoaiKhoanChi.LoaiKhoanChiDto;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanChiResponse;
import com.example.quanlychitieuv2.entity.KhoanThu;
import com.example.quanlychitieuv2.entity.LoaiKhoanChi;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.KhoanThuServiceImpl;
import com.example.quanlychitieuv2.service.impl.LoaiKhoanChiSerViceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/khoan-thu")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhoanThuController extends AbstractBaseController<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> {

    KhoanThuServiceImpl khoanThuServiceImpl;

    @Override
    protected AbstractBaseService<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> abstractService() {
        return khoanThuServiceImpl;
    }
}
