package com.example.quanlychitieuv2.service;

import com.example.quanlychitieuv2.dto.ThongKeTheoThangResponse;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

public interface ThongKeThuByViTien {

    ThongKeTheoThangResponse thongKeThuByViTienTheoThang(int viTienId, YearMonth thoiGian);
    List<ThongKeTheoThangResponse> thongKeThuByViTienTheoNam(int viTienId, Year thoiGian);
}