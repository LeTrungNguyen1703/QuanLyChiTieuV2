package com.example.quanlychitieuv2.dto;

import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.entity.KhoanThu;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeTheoThangResponse {
    String thoiGian;
    Double tongThu;
    Integer soGiaoDich;
    List<ThongKeTheoNgayResponse> thongKeTheoNgays;
}
