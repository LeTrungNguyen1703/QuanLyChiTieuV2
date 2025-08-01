package com.example.quanlychitieuv2.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.KhoanChi}
 */
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KhoanChiResponse implements Serializable {
    private Integer id;
    private String kcTen;
    private BigDecimal kcSotien;
    private String kcMota;
    private Boolean kcLaplai;
    private LocalDate kcNgayketthuclaplai;
    private LoaiKhoanChiDto lkc;
    private ViTienDto vt;
    @NotNull
    private PhuongThucThanhToanResponse pttt;
    KhoanChiResponse.NgayDto ngay;
    KhoanChiResponse.UserDto user;

    /**
     * DTO for {@link com.example.quanlychitieuv2.entity.LoaiKhoanChi}
     */
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    public static class LoaiKhoanChiDto implements Serializable {
        private final Integer id;
        @NotNull
        @Size(max = 255)
        private final String lkcTen;
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
        private final BigDecimal vtSodu;
    }

    @AllArgsConstructor
    @Getter
    public static class NgayDto implements Serializable {
        private final Integer id;
        private final LocalDate ngayDaydu;
    }

    @AllArgsConstructor
    @Getter
    public static class UserDto implements Serializable {
        private final Integer id;
        @NotNull
        @Size(max = 255)
        private final String ndTen;
    }
}