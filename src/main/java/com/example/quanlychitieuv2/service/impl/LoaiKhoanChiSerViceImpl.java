package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.LoaiKhoanChi.LoaiKhoanChiDto;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanChiResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanChi;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.LoaiKhoanChiMapper;
import com.example.quanlychitieuv2.repository.LoaiKhoanChiRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoaiKhoanChiSerViceImpl extends AbstractBaseService<LoaiKhoanChiDto, LoaiKhoanChiResponse, LoaiKhoanChi, Integer> {

    LoaiKhoanChiMapper loaiKhoanChiMapper;

    public LoaiKhoanChiSerViceImpl(LoaiKhoanChiRepository loaiKhoanChiRepository,LoaiKhoanChiMapper loaiKhoanChiMapper) {
        super(loaiKhoanChiRepository);
        this.loaiKhoanChiMapper = loaiKhoanChiMapper;
    }

    @Override
    public BaseMapper<LoaiKhoanChiDto, LoaiKhoanChiResponse, LoaiKhoanChi> getMapper() {
        return loaiKhoanChiMapper;
    }


}
