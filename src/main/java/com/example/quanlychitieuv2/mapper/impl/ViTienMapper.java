package com.example.quanlychitieuv2.mapper.impl;


import com.example.quanlychitieuv2.dto.request.ViTienDto;
import com.example.quanlychitieuv2.dto.response.ViTienResponse;
import com.example.quanlychitieuv2.entity.ViTien;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ViTienMapper extends BaseMapper<ViTienDto, ViTienResponse, ViTien> {

    @Override
    @Mapping(source = "lv.id", target = "lvId")
    @Mapping(source = "lv.lvTen", target = "lvTen")
    ViTienResponse toRes(ViTien entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lv", ignore = true)
    ViTien updateEntity(ViTienDto viTienDto, @MappingTarget ViTien entity);


}
