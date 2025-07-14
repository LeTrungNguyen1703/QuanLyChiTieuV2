package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.request.LoaiKhoanChiDto;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanChiResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanChi;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.LoaiKhoanChiSerViceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@RestController
@RequestMapping("/loai-khoan-chi")
public class LoaiKhoanChiController extends AbstractBaseController<LoaiKhoanChiDto, LoaiKhoanChiResponse, LoaiKhoanChi,Integer> {

    private final LoaiKhoanChiSerViceImpl loaiKhoanChiSerViceImpl;

    public LoaiKhoanChiController(LoaiKhoanChiSerViceImpl loaiKhoanChiSerVice) {
        this.loaiKhoanChiSerViceImpl = loaiKhoanChiSerVice;
    }

    @Override
    protected AbstractBaseService<LoaiKhoanChiDto, LoaiKhoanChiResponse, LoaiKhoanChi, Integer> abstractService() {
        return loaiKhoanChiSerViceImpl;
    }
}
