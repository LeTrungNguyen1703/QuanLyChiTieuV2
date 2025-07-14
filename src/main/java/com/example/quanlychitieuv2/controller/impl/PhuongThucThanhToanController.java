package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.PhuongThucThanhToanRequest;
import com.example.quanlychitieuv2.dto.response.PhuongThucThanhToanResponse;
import com.example.quanlychitieuv2.entity.PhuongThucThanhToan;
import com.example.quanlychitieuv2.mapper.impl.PhuongThucThanhToanMapperImpl;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.PhuongThucThanhToanServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pttt")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PhuongThucThanhToanController extends AbstractBaseController<PhuongThucThanhToanRequest, PhuongThucThanhToanResponse, PhuongThucThanhToan,Integer> {

    PhuongThucThanhToanServiceImpl phuongThucThanhToanServiceImpl;

    @Override
    protected AbstractBaseService<PhuongThucThanhToanRequest, PhuongThucThanhToanResponse, PhuongThucThanhToan, Integer> abstractService() {
        return phuongThucThanhToanServiceImpl;
    }
}
