package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.PhanQuyenRequest;
import com.example.quanlychitieuv2.dto.response.PhanQuyenResponse;
import com.example.quanlychitieuv2.entity.PhanQuyen;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhanQuyenMapper extends BaseMapper<PhanQuyenRequest, PhanQuyenResponse, PhanQuyen> {
    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PhanQuyen updateEntity(PhanQuyenRequest phanQuyenRequest, @MappingTarget PhanQuyen entity);
}
