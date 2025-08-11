package com.example.quanlychitieuv2.dto.response;

import com.example.quanlychitieuv2.entity.AuditFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.KhoanThu}
 */
@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KhoanThuResponse implements Serializable {
    private Integer id;
    private String moTa;
    private String tenKhoanThu;
    private BigDecimal ktSotien;
    private AuditFields auditFields;
    
    private KhoanThuResponse.UserDto user;
    private KhoanThuResponse.NgayDto ngay;
    private KhoanThuResponse.ViTienDto vt;
    private KhoanThuResponse.LoaiKhoanThuDto lkt;


    /**
     * DTO for {@link com.example.quanlychitieuv2.entity.User}
     */
    @AllArgsConstructor
    @Getter
    public static class UserDto implements Serializable {
        private final Integer id;
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
        private final Double vtSodu;
    }

    /**
     * DTO for {@link com.example.quanlychitieuv2.entity.LoaiKhoanThu}
     */
    @AllArgsConstructor
    @Getter
    public static class LoaiKhoanThuDto implements Serializable {
        private final Integer id;
        private final String lktTen;
    }
}