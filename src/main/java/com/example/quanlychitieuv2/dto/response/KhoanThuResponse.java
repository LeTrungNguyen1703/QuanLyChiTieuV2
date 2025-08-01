package com.example.quanlychitieuv2.dto.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.KhoanThu}
 */
@AllArgsConstructor
@Getter
public class KhoanThuResponse implements Serializable {
    private Integer id;
    private String moTa;
    private String tenKhoanThu;
    private BigDecimal ktSotien;
    @NotNull
    private KhoanThuResponse.UserDto user;
    @NotNull
    private KhoanThuResponse.NgayDto ngay;
    @NotNull
    private KhoanThuResponse.ViTienDto vt;
    @NotNull
    private KhoanThuResponse.LoaiKhoanThuDto lkt;

    @NotNull

    /**
     * DTO for {@link com.example.quanlychitieuv2.entity.User}
     */
    @AllArgsConstructor
    @Getter
    public static class UserDto implements Serializable {
        private final Integer id;
        @NotNull
        @Size(max = 255)
        private final String ndTen;
    }

    /**
     * DTO for {@link com.example.quanlychitieuv2.entity.Ngay}
     */
    @AllArgsConstructor
    @Getter
    public static class NgayDto implements Serializable {
        private final Integer id;
        private final LocalDate ngayDaydu;
    }

    /**
     * DTO for {@link com.example.quanlychitieuv2.entity.ViTien}
     */
    @AllArgsConstructor
    @Getter
    public static class ViTienDto implements Serializable {
        private final Integer id;
        private final String tenVi;
        @NotNull
        private final Double vtSodu;
    }

    /**
     * DTO for {@link com.example.quanlychitieuv2.entity.LoaiKhoanThu}
     */
    @AllArgsConstructor
    @Getter
    public static class LoaiKhoanThuDto implements Serializable {
        private final Integer id;
        @NotNull
        @Size(max = 256)
        private final String lktTen;
    }
}