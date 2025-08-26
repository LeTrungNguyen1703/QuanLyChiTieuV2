package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.ApiResponseSuccess;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNamResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNgayResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoThangResponse;
import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.entity.KhoanThu;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.AbstractThongKeService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

@RestController
@RequestMapping("/khoan-thu")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class KhoanThuController extends AbstractBaseController<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> {

    @Qualifier("khoanThuServiceImpl")
    AbstractBaseService<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> khoanThuServiceImpl;
    
    @Qualifier("thongKeThuServiceImpl")
    AbstractThongKeService<KhoanThu,KhoanThuResponse> thongKeService;

    public KhoanThuController(AbstractBaseService<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> khoanThuServiceImpl, AbstractThongKeService<KhoanThu,KhoanThuResponse> thongKeService) {
        this.khoanThuServiceImpl = khoanThuServiceImpl;
        this.thongKeService = thongKeService;
    }


    @Override
    protected AbstractBaseService<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> abstractService() {
        return khoanThuServiceImpl;
    }

    @Override
    @PreAuthorize("@walletSecurityFacade.hasWalletPermission(#khoanThuRequest.vtId, @addTransaction) and @walletSecurityFacade.hasWalletAccess(#khoanThuRequest.vtId)")
    public ResponseEntity<ApiResponseSuccess<KhoanThuResponse>> create(KhoanThuRequest khoanThuRequest) {
        return super.create(khoanThuRequest);
    }

    @GetMapping("/thong-ke-theo-ngay")
    public ResponseEntity<ApiResponseSuccess<?>> thongKeTheoNgay(
            @RequestParam Integer viTienId,
            @RequestParam LocalDate thoiGian
    ) {

        ThongKeTheoNgayResponse<?> thongKeTheoNgayResponse = thongKeService.thongKeTheoNgay(viTienId, thoiGian);
        return ResponseEntity.ok(new ApiResponseSuccess<>(thongKeTheoNgayResponse));
    }

    @GetMapping("/thong-ke-theo-thang")
    public ResponseEntity<ApiResponseSuccess<?>> thongKeTheoThang(
            @RequestParam Integer viTienId,
            @RequestParam YearMonth thoiGian
            ) {

        ThongKeTheoThangResponse<?> thongKeTheoThangRespons = thongKeService.thongKeByViTienTheoThang(viTienId, thoiGian);
        return ResponseEntity.ok(new ApiResponseSuccess<>(thongKeTheoThangRespons));
    }

    @GetMapping("/thong-ke-theo-nam")
    public ResponseEntity<ApiResponseSuccess<?>> thongKeTheoNam(
            @RequestParam Integer viTienId,
            @RequestParam Year thoiGian
    ) {

        ThongKeTheoNamResponse<?> thongKeTheoThangRespons = thongKeService.thongKeByViTienTheoNam(viTienId, thoiGian);
        return ResponseEntity.ok(new ApiResponseSuccess<>(thongKeTheoThangRespons));
    }

}
