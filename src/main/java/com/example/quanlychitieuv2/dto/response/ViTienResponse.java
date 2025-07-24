package com.example.quanlychitieuv2.dto.response;

import com.example.quanlychitieuv2.enums.TrangThaiHoatDong;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.ViTien}
 */
@Getter
@AllArgsConstructor
@Setter
public class ViTienResponse implements Serializable {
    private Integer id;
    private String tenVi;
    private BigDecimal vtSodu;
    private TrangThaiHoatDong trangThaiHoatDong;
    private LoaiViResponse loaiVi;
    private ViTienResponse.UserResponse nguoiDung;

    @Getter
    @AllArgsConstructor
    public static class UserResponse {
        private Integer id;
        private String ndTen;
    }

}