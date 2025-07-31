package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AbstractBaseController;
import com.example.quanlychitieuv2.dto.ApiResponseSuccess;
import com.example.quanlychitieuv2.dto.ThongKeTheoNamResponse;
import com.example.quanlychitieuv2.dto.ThongKeTheoThangResponse;
import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.entity.KhoanThu;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.impl.KhoanThuServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.time.YearMonth;

@RestController
@RequestMapping("/khoan-thu")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class KhoanThuController extends AbstractBaseController<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> {

    KhoanThuServiceImpl khoanThuServiceImpl;
    @Override
    protected AbstractBaseService<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> abstractService() {
        return khoanThuServiceImpl;
    }

    @Override
    @PreAuthorize("@walletSecurity.hasWalletPermission(#khoanThuRequest.vtId, @addTransaction)")
    public ResponseEntity<ApiResponseSuccess<KhoanThuResponse>> create(KhoanThuRequest khoanThuRequest) {
        log.info("Spring context {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return super.create(khoanThuRequest);
    }

    @GetMapping("/thong-ke-theo-thang")
    public ResponseEntity<ApiResponseSuccess<?>> thongKeTheoThang(
            @RequestParam Integer viTienId,
            @RequestParam YearMonth thoiGian
            ) {

        ThongKeTheoThangResponse<?> thongKeTheoThangRespons = khoanThuServiceImpl.thongKeThuByViTienTheoThang(viTienId, thoiGian);
        return ResponseEntity.ok(new ApiResponseSuccess<>(thongKeTheoThangRespons));
    }

    @GetMapping("/thong-ke-theo-nam")
    public ResponseEntity<ApiResponseSuccess<?>> thongKeTheoNam(
            @RequestParam Integer viTienId,
            @RequestParam Year thoiGian
    ) {

        ThongKeTheoNamResponse<?> thongKeTheoThangRespons = khoanThuServiceImpl.thongKeThuByViTienTheoNam(viTienId, thoiGian);
        return ResponseEntity.ok(new ApiResponseSuccess<>(thongKeTheoThangRespons));
    }

}
