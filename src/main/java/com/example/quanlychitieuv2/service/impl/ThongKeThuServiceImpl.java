package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNamResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNgayResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoThangResponse;
import com.example.quanlychitieuv2.entity.KhoanThu;
import com.example.quanlychitieuv2.entity.User;
import com.example.quanlychitieuv2.entity.ViTien;
import com.example.quanlychitieuv2.enums.LoaiGiaoDich;
import com.example.quanlychitieuv2.repository.KhoanThuRepository;
import com.example.quanlychitieuv2.service.ThongKeThuChiByViTien;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Service xử lý các thao tác liên quan đến Khoản Thu
 * Cung cấp các phương thức CRUD và thống kê dữ liệu khoản thu theo ngày, tháng, năm
 */
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongKeThuServiceImpl extends ThongKeServiceImpl<KhoanThu,KhoanThuResponse> {

    KhoanThuRepository khoanThuRepository;

    @Autowired
    public ThongKeThuServiceImpl(FindBy findBy, KhoanThuRepository khoanThuRepository) {
        super(findBy);
        this.khoanThuRepository = khoanThuRepository;
    }


    @Override
    public List<KhoanThu> getListByDay(ViTien viTien, LocalDate ngay_ngayDaydu) {
        return khoanThuRepository.findByVtAndNgayNgayDaydu(viTien, ngay_ngayDaydu);
    }

    @Override
    public Double getSoTien(KhoanThu item) {
        return item.getKtSotien().doubleValue();
    }

    @Override
    protected LoaiGiaoDich getLoaiGiaoDich() {
        return LoaiGiaoDich.THU;
    }

    @Override
    protected List<KhoanThu> getListByMonth(Integer viTienId, LocalDate startDate, LocalDate endDate) {
        return khoanThuRepository.findByVtAndThang(viTienId, startDate, endDate);
    }

    @Override
    protected LocalDate getNgayDaydu(KhoanThu item) {
        return item.getNgay().getNgayDaydu();
    }

    @Override
    protected List<KhoanThuResponse> mapListToListRes(List<KhoanThu> item) {
        return item.stream()
                .map(khoanThu -> KhoanThuResponse.builder()
                        .id(khoanThu.getId())
                        .tenKhoanThu(khoanThu.getTenKhoanThu())
                        .moTa(khoanThu.getMoTa())
                        .ktSotien(khoanThu.getKtSotien())
                        .auditFields(khoanThu.getAuditFields())
                        .build())
                .collect(Collectors.toList());
    }
}
