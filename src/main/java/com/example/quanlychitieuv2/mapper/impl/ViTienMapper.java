package com.example.quanlychitieuv2.mapper.impl;


import com.example.quanlychitieuv2.dto.request.ViTienDto;
import com.example.quanlychitieuv2.dto.response.ViTienResponse;
import com.example.quanlychitieuv2.entity.ViTien;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ViTienMapper extends BaseMapper<ViTienDto, ViTienResponse, ViTien> {

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ViTien updateEntity(ViTienDto viTienDto, @MappingTarget ViTien entity);
}
