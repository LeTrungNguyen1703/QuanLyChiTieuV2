package com.example.quanlychitieuv2.mapper.impl;


import com.example.quanlychitieuv2.dto.request.ViTienDto;
import com.example.quanlychitieuv2.dto.response.ViTienResponse;
import com.example.quanlychitieuv2.entity.SoHu;
import com.example.quanlychitieuv2.entity.User;
import com.example.quanlychitieuv2.entity.ViTien;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ViTienMapper extends BaseMapper<ViTienDto, ViTienResponse, ViTien> {

    @Override
    @Mapping(source = "lv.id", target = "loaiVi.id")
    @Mapping(source = "lv.lvTen", target = "loaiVi.lvTen")
    @Mapping(target = "nguoiDung", expression = "java(findOwnerUser(entity))")
    ViTienResponse toRes(ViTien entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lv", ignore = true)
    ViTien updateEntity(ViTienDto viTienDto, @MappingTarget ViTien entity);

    /**
     * Chuyển đổi từ đối tượng User sang đối tượng UserResponse dùng cho ViTienResponse
     * @param user Đối tượng User cần chuyển đổi
     * @return ViTienResponse.UserResponse chứa thông tin cơ bản của User
     */
    default ViTienResponse.UserResponse toUserRes(User user) {
        if (user == null) {
            return null;
        }
        return new ViTienResponse.UserResponse(user.getId(), user.getNdTen());
    }

    /**
     * Tìm người dùng là chủ sở hữu của ví tiền
     * @param viTien Ví tiền cần tìm chủ sở hữu
     * @return ViTienResponse.UserResponse chứa thông tin người dùng là chủ sở hữu
     */
    default ViTienResponse.UserResponse findOwnerUser(ViTien viTien) {
        if (viTien == null || viTien.getSoHus() == null || viTien.getSoHus().isEmpty()) {
            return null;
        }

        // Tìm bản ghi SoHu có userCapQuyenId là null (chủ sở hữu)
        Optional<SoHu> ownerSoHu = viTien.getSoHus().stream()
                .filter(soHu -> soHu.getUserCapQuyenId() == null)
                .findFirst();

        return ownerSoHu.map(soHu -> toUserRes(soHu.getUserDuocCapQuyenId())).orElse(null);

    }
}
