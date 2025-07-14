package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.TrangThaiRequest;
import com.example.quanlychitieuv2.dto.response.TrangThaiResponse;
import com.example.quanlychitieuv2.entity.TrangThai;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TrangThaiMapper extends BaseMapper<TrangThaiRequest, TrangThaiResponse, TrangThai> {

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TrangThai updateEntity(TrangThaiRequest trangThaiRequest, @MappingTarget TrangThai entity);
}
