package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.LoaiKhoanThuDto;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanThuResponse;
import com.example.quanlychitieuv2.entity.LoaiKhoanThu;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoaiKhoanThuMapper extends BaseMapper<LoaiKhoanThuDto, LoaiKhoanThuResponse, LoaiKhoanThu> {
}
