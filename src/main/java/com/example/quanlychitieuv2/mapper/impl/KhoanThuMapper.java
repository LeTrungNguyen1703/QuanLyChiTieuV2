package com.example.quanlychitieuv2.mapper.impl;

import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface KhoanThuMapper extends BaseMapper<KhoanThuRequest, KhoanThuResponse, KhoanThu> {


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "vt", ignore = true)
    @Mapping(target = "ngay", ignore = true)
    @Mapping(target = "lkt", ignore = true)
    @Override
    KhoanThu toEntity(KhoanThuRequest KhoanThuRequest);

    @Mapping(target = "lkt", source = "lkt")
    @Mapping(target = "ngay", source = "ngay")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "vt", source = "vt")
    @Override
    KhoanThuResponse toRes(KhoanThu entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "vt", ignore = true)
    @Mapping(target = "ngay", ignore = true)
    @Mapping(target = "lkt", ignore = true)
    @Override
    KhoanThu updateEntity(KhoanThuRequest khoanThuRequest, @MappingTarget KhoanThu entity);

    KhoanThuResponse.LoaiKhoanThuDto toLoaiKhoanThuDto(LoaiKhoanThu loaiKhoanThu);

    KhoanThuResponse.NgayDto toNgayDto(Ngay ngay);

    KhoanThuResponse.UserDto toUserDto(User user);

    KhoanThuResponse.ViTienDto toViTienDto(ViTien viTien);
}