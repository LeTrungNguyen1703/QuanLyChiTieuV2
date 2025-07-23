package com.example.quanlychitieuv2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThongKeTheoNamResponse<T> {
    String thoiGian;
    Double tongThu;
    Double thuCaoNhat;
    Double thuTrungBinh;
    Double thuThapNhat;
    List<T> thongKeTheoThangs;
}
