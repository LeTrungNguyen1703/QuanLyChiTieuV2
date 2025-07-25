package com.example.quanlychitieuv2.dto;

import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanThuResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeTheoNgayResponse {
    String thoiGian;
    Double tongThu;
    Double thuCaoNhat;
    Double thuTrungBinh;
    Double thuThapNhat;
    Integer soGiaoDich;

}
