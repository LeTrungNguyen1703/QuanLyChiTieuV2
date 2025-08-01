package com.example.quanlychitieuv2.dto.response.ThongKeThuChi;

import com.example.quanlychitieuv2.enums.LoaiGiaoDich;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class ThongKeThuChiResponse {
    String thoiGian;
    Double tongSoTien;
    Double soTienCaoNhat;
    Double soTienTrungBinh;
    Double soTienThapNhat;
    Integer soGiaoDich;
    LoaiGiaoDich loaiGiaoDich;

}
