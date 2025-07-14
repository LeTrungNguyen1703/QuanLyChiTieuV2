package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.TrangThaiRequest;
import com.example.quanlychitieuv2.dto.response.TrangThaiResponse;
import com.example.quanlychitieuv2.entity.TrangThai;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.TrangThaiServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trang-thai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TrangThaiController extends AbstractBaseController<TrangThaiRequest, TrangThaiResponse, TrangThai, Integer> {

    TrangThaiServiceImpl trangThaiService;

    @Override
    protected AbstractBaseService<TrangThaiRequest, TrangThaiResponse, TrangThai, Integer> abstractService() {
        return trangThaiService;
    }
}
