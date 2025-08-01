package com.example.quanlychitieuv2.service;

import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNamResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoThangResponse;

import java.time.Year;
import java.time.YearMonth;

public interface ThongKeThuChiByViTien {


    ThongKeTheoThangResponse<?> thongKeByViTienTheoThang(int viTienId, YearMonth thoiGian);

    ThongKeTheoNamResponse<?> thongKeByViTienTheoNam(int viTienId, Year thoiGian);
}