package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.LoaiKhoanChi.LoaiKhoanChiDto;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanChiResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanChi;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoaiKhoanChiMapper extends BaseMapper<LoaiKhoanChiDto, LoaiKhoanChiResponse, LoaiKhoanChi> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Override
    LoaiKhoanChi updateEntity(LoaiKhoanChiDto loaiKhoanChiDto, @MappingTarget LoaiKhoanChi entity);
}
