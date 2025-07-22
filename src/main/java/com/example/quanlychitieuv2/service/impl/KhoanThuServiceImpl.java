package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.ThongKeTheoNgayResponse;
import com.example.quanlychitieuv2.dto.ThongKeTheoThangResponse;
import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.dto.response.LoaiKhoanThuResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.KhoanThuMapper;
import com.example.quanlychitieuv2.repository.KhoanThuRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.ThongKeThuByViTien;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhoanThuServiceImpl extends AbstractBaseService<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> implements ThongKeThuByViTien {

    KhoanThuRepository khoanThuRepository;
    KhoanThuMapper khoanThuMapper;
    FindBy findBy;

    public KhoanThuServiceImpl(JpaRepository<KhoanThu, Integer> jpaRepository, KhoanThuRepository khoanThuRepository, KhoanThuMapper khoanThuMapper, FindBy findBy) {
        super(jpaRepository);
        this.khoanThuRepository = khoanThuRepository;
        this.khoanThuMapper = khoanThuMapper;
        this.findBy = findBy;
    }

    @Override
    protected BaseMapper<KhoanThuRequest, KhoanThuResponse, KhoanThu> getMapper() {
        return khoanThuMapper;
    }

    @Override
    public KhoanThuResponse create(KhoanThuRequest khoanThuRequest) {
        KhoanThu khoanThu = khoanThuMapper.toEntity(khoanThuRequest);
        ViTien viTien = findBy.findViTienById(khoanThuRequest.getVtId());
        User user = findBy.findNguoiDungById(khoanThuRequest.getUserId());
        LoaiKhoanThu loaiKhoanThu = findBy.findLoaiKhoanThuById(khoanThuRequest.getLktId());
        Ngay ngay = new Ngay();
        ngay = findBy.findNgayByNgayDayDu(ngay.getNgayDaydu());

        khoanThu.setVt(viTien);
        khoanThu.setUser(user);
        khoanThu.setLkt(loaiKhoanThu);
        khoanThu.setNgay(ngay);

        this.tinhSoDu(khoanThu);

        khoanThu = khoanThuRepository.save(khoanThu);

        return khoanThuMapper.toRes(khoanThu);
    }

    private void tinhSoDu(KhoanThu khoanThu) {

        BigDecimal tongTien = khoanThu.getVt().getVtSodu().add(khoanThu.getKtSotien());

        khoanThu.getVt().setVtSodu(tongTien);

    }

    @Override
    public List<ThongKeTheoThangResponse> thongKeThuByViTienTheoThang(int viTienId, YearMonth thoiGian) {
        findBy.findViTienById(viTienId);
        LocalDate startDate = thoiGian.atDay(1);
        LocalDate endDate = thoiGian.atEndOfMonth();


        List<KhoanThu> khoanThus = khoanThuRepository.findByVtAndThang(viTienId, startDate, endDate);

        Map<LocalDate, List<KhoanThu>> groupedByDate = khoanThus.stream()
                .collect(Collectors.groupingBy(khoanThu -> khoanThu.getNgay().getNgayDaydu()));

        List<ThongKeTheoNgayResponse> thongKeTheoNgays = groupedByDate.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<KhoanThu> listInDay = entry.getValue();

                    double tongTien = listInDay.stream()
                            .mapToDouble(kt -> kt.getKtSotien().doubleValue())
                            .sum();

                    int soGiaoDich = listInDay.size();

                    return ThongKeTheoNgayResponse.builder()
                            .tongThu(tongTien)
                            .soGiaoDich(soGiaoDich)
                            .thoiGian(date.toString())
                            .build();
                })
                .sorted(Comparator.comparing(ThongKeTheoNgayResponse::getThoiGian)) // Sắp xếp theo ngày tăng dần
                .collect(Collectors.toList());


        DoubleSummaryStatistics statistics = khoanThus.stream()
                .mapToDouble(khoanThu -> khoanThu.getKtSotien().doubleValue())
                .summaryStatistics();

        return List.of(new ThongKeTheoThangResponse(
                thoiGian.toString(),
                statistics.getSum(),
                (int) statistics.getCount(),
                thongKeTheoNgays

        ));
    }
}
