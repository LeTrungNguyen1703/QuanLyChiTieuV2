package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.PhanQuyenRequest;
import com.example.quanlychitieuv2.dto.response.PhanQuyenResponse;
import com.example.quanlychitieuv2.entity.PhanQuyen;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.PhanQuyenServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phan-quyen")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PhanQuyenController extends AbstractBaseController<PhanQuyenRequest, PhanQuyenResponse, PhanQuyen, Integer> {

    PhanQuyenServiceImpl phanQuyenServiceImpl;

    @Override
    protected AbstractBaseService<PhanQuyenRequest, PhanQuyenResponse, PhanQuyen, Integer> abstractService() {
        return phanQuyenServiceImpl;
    }
}
