package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.KhoanChiRequest;
import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanChiResponse;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface KhoanChiMapper extends BaseMapper<KhoanChiRequest, KhoanChiResponse, KhoanChi> {


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "vt", ignore = true)
    @Mapping(target = "ngay", ignore = true)
    @Mapping(target = "lkc", ignore = true)
    @Mapping(target = "pttt", ignore = true)
    @Override
    KhoanChi toEntity(KhoanChiRequest khoanChiRequest);

    @Mapping(target = "lkc", source = "lkc")
    @Mapping(target = "ngay", source = "ngay")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "vt", source = "vt")
    @Override
    KhoanChiResponse toRes(KhoanChi entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "vt", ignore = true)
    @Mapping(target = "ngay", ignore = true)
    @Mapping(target = "lkc", ignore = true)
    @Override
    KhoanChi updateEntity(KhoanChiRequest khoanChiRequest, @MappingTarget KhoanChi entity);

    KhoanThuResponse.LoaiKhoanThuDto toLoaiKhoanThuDto(LoaiKhoanThu loaiKhoanThu);

    KhoanThuResponse.NgayDto toNgayDto(Ngay ngay);

    KhoanThuResponse.UserDto toUserDto(User user);

    KhoanThuResponse.ViTienDto toViTienDto(ViTien viTien);
}