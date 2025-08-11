package com.example.quanlychitieuv2.dto.response.ThongKeThuChi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@AllArgsConstructor
@SuperBuilder
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThongKeTheoThangResponse<T> extends  ThongKeThuChiResponse {
    List<T> thongKeTheoNgays;
}