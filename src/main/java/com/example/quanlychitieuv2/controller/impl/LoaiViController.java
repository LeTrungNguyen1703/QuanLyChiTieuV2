package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.LoaiViRequest;
import com.example.quanlychitieuv2.dto.response.LoaiViResponse;
import com.example.quanlychitieuv2.entity.LoaiVi;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.LoaiViServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loai-vi")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiViController extends AbstractBaseController<LoaiViRequest, LoaiViResponse, LoaiVi, Integer> {

    LoaiViServiceImpl loaiViService;

    @Override
    protected AbstractBaseService<LoaiViRequest, LoaiViResponse, LoaiVi, Integer> abstractService() {
        return loaiViService;
    }
}
