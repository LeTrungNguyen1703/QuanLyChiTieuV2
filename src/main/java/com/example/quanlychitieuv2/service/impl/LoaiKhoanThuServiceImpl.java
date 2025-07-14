package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.LoaiKhoanThuDto;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanThuResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanThu;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.LoaiKhoanThuMapper;
import com.example.quanlychitieuv2.repository.LoaiKhoanThuRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiKhoanThuServiceImpl extends AbstractBaseService<LoaiKhoanThuDto, LoaiKhoanThuResponse, LoaiKhoanThu,Integer> {

    LoaiKhoanThuMapper loaiKhoanThuMapper;

    public LoaiKhoanThuServiceImpl(LoaiKhoanThuMapper loaiKhoanThuMapper, LoaiKhoanThuRepository loaiKhoanThuRepository) {
        super(loaiKhoanThuRepository);
        this.loaiKhoanThuMapper = loaiKhoanThuMapper;
    }

    @Override
    protected BaseMapper<LoaiKhoanThuDto, LoaiKhoanThuResponse, LoaiKhoanThu> getMapper() {
        return loaiKhoanThuMapper;
    }
}
