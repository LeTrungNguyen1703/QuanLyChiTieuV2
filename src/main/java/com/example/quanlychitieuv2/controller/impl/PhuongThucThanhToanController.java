package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.PhuongThucThanhToanRequest;
import com.example.quanlychitieuv2.dto.response.PhuongThucThanhToanResponse;
import com.example.quanlychitieuv2.entity.PhuongThucThanhToan;
import com.example.quanlychitieuv2.mapper.impl.PhuongThucThanhToanMapperImpl;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.PhuongThucThanhToanServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pttt")
public class PhuongThucThanhToanController extends AbstractBaseController<PhuongThucThanhToanRequest, PhuongThucThanhToanResponse, PhuongThucThanhToan,Integer> {

    PhuongThucThanhToanServiceImpl phuongThucThanhToanServiceImpl;

    public PhuongThucThanhToanController(PhuongThucThanhToanServiceImpl phuongThucThanhToanServiceImpl) {
        this.phuongThucThanhToanServiceImpl = phuongThucThanhToanServiceImpl;
    }

    @Override
    protected AbstractBaseService<PhuongThucThanhToanRequest, PhuongThucThanhToanResponse, PhuongThucThanhToan, Integer> abstractService() {
        return phuongThucThanhToanServiceImpl;
    }
}
