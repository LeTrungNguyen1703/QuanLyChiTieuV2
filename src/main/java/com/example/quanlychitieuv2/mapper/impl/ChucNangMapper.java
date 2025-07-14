package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.ChucNangRequest;
import com.example.quanlychitieuv2.dto.response.ChucNangResponse;
import com.example.quanlychitieuv2.entity.ChucNang;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChucNangMapper extends BaseMapper<ChucNangRequest, ChucNangResponse, ChucNang> {

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ChucNang updateEntity(ChucNangRequest chucNangRequest, @MappingTarget ChucNang entity);
}
