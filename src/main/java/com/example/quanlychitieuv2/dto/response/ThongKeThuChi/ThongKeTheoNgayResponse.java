package com.example.quanlychitieuv2.dto.response.ThongKeThuChi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThongKeTheoNgayResponse <T> extends ThongKeThuChiResponse {
    T thongKeTheoThoiGianTrongNgay;
}
