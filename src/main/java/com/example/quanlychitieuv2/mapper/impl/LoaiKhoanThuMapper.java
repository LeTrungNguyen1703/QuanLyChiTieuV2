package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.LoaiKhoanThu.LoaiKhoanThuDto;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanThuResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanThu;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoaiKhoanThuMapper extends BaseMapper<LoaiKhoanThuDto, LoaiKhoanThuResponse, LoaiKhoanThu> {
    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LoaiKhoanThu updateEntity(LoaiKhoanThuDto loaiKhoanThuDto, @MappingTarget LoaiKhoanThu entity);
}
