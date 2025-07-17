package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.LoaiVi.LoaiViRequest;
import com.example.quanlychitieuv2.dto.response.LoaiViResponse;
import com.example.quanlychitieuv2.entity.LoaiVi;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoaiViMapper extends BaseMapper<LoaiViRequest, LoaiViResponse, LoaiVi> {
    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LoaiVi updateEntity(LoaiViRequest loaiViRequest, @MappingTarget LoaiVi entity);
}
