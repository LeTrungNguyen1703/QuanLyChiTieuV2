package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.PhuongThucThanhToanRequest;
import com.example.quanlychitieuv2.dto.response.PhuongThucThanhToanResponse;
import com.example.quanlychitieuv2.entity.PhuongThucThanhToan;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhuongThucThanhToanMapper extends BaseMapper<PhuongThucThanhToanRequest, PhuongThucThanhToanResponse, PhuongThucThanhToan> {
    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PhuongThucThanhToan updateEntity(PhuongThucThanhToanRequest phuongThucThanhToanRequest, @MappingTarget PhuongThucThanhToan entity);
}
