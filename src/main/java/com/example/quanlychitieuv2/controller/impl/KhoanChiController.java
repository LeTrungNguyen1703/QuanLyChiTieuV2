package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.ApiResponseSuccess;
import com.example.quanlychitieuv2.dto.request.KhoanChiRequest;
import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanChiResponse;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNamResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoThangResponse;
import com.example.quanlychitieuv2.entity.KhoanChi;
import com.example.quanlychitieuv2.entity.KhoanThu;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.KhoanChiServiceImpl;
import com.example.quanlychitieuv2.service.impl.KhoanThuServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.time.YearMonth;

@RestController
@RequestMapping("/khoan-chi")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class KhoanChiController extends AbstractBaseController<KhoanChiRequest, KhoanChiResponse, KhoanChi, Integer> {

    KhoanChiServiceImpl khoanChiServiceImpl;
    @Override
    protected AbstractBaseService<KhoanChiRequest, KhoanChiResponse, KhoanChi, Integer> abstractService() {
        return khoanChiServiceImpl;
    }

    @Override
    @PreAuthorize("@walletSecurity.hasWalletPermission(#khoanChiRequest.vtId, @addTransaction) and @walletSecurity.hasWalletAccess(#khoanChiRequest.vtId)")
    public ResponseEntity<ApiResponseSuccess<KhoanChiResponse>> create(KhoanChiRequest khoanChiRequest) {
        return super.create(khoanChiRequest);
    }

    @GetMapping("/thong-ke-theo-thang")
    public ResponseEntity<ApiResponseSuccess<?>> thongKeTheoThang(
            @RequestParam Integer viTienId,
            @RequestParam YearMonth thoiGian
            ) {

        ThongKeTheoThangResponse<?> thongKeTheoThangRespons = khoanChiServiceImpl.thongKeByViTienTheoThang(viTienId, thoiGian);
        return ResponseEntity.ok(new ApiResponseSuccess<>(thongKeTheoThangRespons));
    }

    @GetMapping("/thong-ke-theo-nam")
    public ResponseEntity<ApiResponseSuccess<?>> thongKeTheoNam(
            @RequestParam Integer viTienId,
            @RequestParam Year thoiGian
    ) {

        ThongKeTheoNamResponse<?> thongKeTheoThangRespons = khoanChiServiceImpl.thongKeByViTienTheoNam(viTienId, thoiGian);
        return ResponseEntity.ok(new ApiResponseSuccess<>(thongKeTheoThangRespons));
    }

}
