package com.example.quanlychitieuv2.service;

import com.example.quanlychitieuv2.dto.ThongKeTheoThangResponse;

import java.time.YearMonth;
import java.util.List;

public interface ThongKeThuByViTien {

    List<ThongKeTheoThangResponse> thongKeThuByViTienTheoThang(int viTienId, YearMonth thoiGian);
}