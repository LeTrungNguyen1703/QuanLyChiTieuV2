package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.ThongKeTheoNamResponse;
import com.example.quanlychitieuv2.dto.ThongKeTheoNgayResponse;
import com.example.quanlychitieuv2.dto.ThongKeTheoThangResponse;
import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
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
import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public List<ThongKeTheoNgayResponse> thongKeThuByViTienTheoNgay(List<KhoanThu> khoanThus) {

        Map<LocalDate, List<KhoanThu>> groupedByDate = khoanThus.stream()
                .collect(Collectors.groupingBy(khoanThu -> khoanThu.getNgay().getNgayDaydu()));

        return groupedByDate.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<KhoanThu> listInDay = entry.getValue();

                    DoubleSummaryStatistics doubleSummaryStatistics = listInDay.stream()
                            .mapToDouble(kt -> kt.getKtSotien().doubleValue())
                            .summaryStatistics();

                    return ThongKeTheoNgayResponse.builder()
                            .tongThu(doubleSummaryStatistics.getSum())
                            .thuCaoNhat(doubleSummaryStatistics.getMax())
                            .thuTrungBinh(doubleSummaryStatistics.getAverage())
                            .thuThapNhat(doubleSummaryStatistics.getMin())
                            .soGiaoDich((int) doubleSummaryStatistics.getCount())
                            .thoiGian(date.toString())
                            .build();

                })
                .sorted(Comparator.comparing(ThongKeTheoNgayResponse::getThoiGian)) // Sắp xếp theo ngày tăng dần
                .toList();
    }


    @Override
    public ThongKeTheoThangResponse<?> thongKeThuByViTienTheoThang(int viTienId, YearMonth thoiGian) {
        findBy.findViTienById(viTienId);

        List<KhoanThu> khoanThus = this.findKhoanThuTheoThang(viTienId, thoiGian);
        if (khoanThus.isEmpty()) {
            return null;
        }

        List<ThongKeTheoNgayResponse> thongKeTheoNgays = this.thongKeThuByViTienTheoNgay(khoanThus);

        DoubleSummaryStatistics statistics = khoanThus.stream()
                .mapToDouble(khoanThu -> khoanThu.getKtSotien().doubleValue())
                .summaryStatistics();


        return ThongKeTheoThangResponse.<ThongKeTheoNgayResponse>builder()
                .thoiGian(thoiGian.toString())
                .tongThu(statistics.getSum())
                .thuCaoNhat(statistics.getMax())
                .thuThapNhat(statistics.getMin())
                .thuTrungBinh(statistics.getAverage())
                .soGiaoDich((int) statistics.getCount())
                .thongKeTheoNgays(thongKeTheoNgays)
                .build();
    }

    @Override
    public ThongKeTheoNamResponse<?> thongKeThuByViTienTheoNam(int viTienId, Year thoiGian) {

        List<YearMonth> yearMonths = this.parseYearToYearMonths(thoiGian);

        List<ThongKeTheoThangResponse<?>> thongKeTheoThangResponses = this.parseToThongKeTheoThangResponses(viTienId, yearMonths);

        DoubleSummaryStatistics doubleSummaryStatistics = thongKeTheoThangResponses.stream().mapToDouble(ThongKeTheoThangResponse::getTongThu).summaryStatistics();

        return ThongKeTheoNamResponse.<ThongKeTheoThangResponse<?>>builder()
                .thoiGian(thoiGian.toString())
                .tongThu(doubleSummaryStatistics.getSum())
                .thuCaoNhat(doubleSummaryStatistics.getMax())
                .thuTrungBinh(doubleSummaryStatistics.getAverage())
                .thuThapNhat(doubleSummaryStatistics.getMin())
                .thongKeTheoThangs(thongKeTheoThangResponses)
                .build();
    }

    private void tinhSoDu(KhoanThu khoanThu) {

        BigDecimal tongTien = khoanThu.getVt().getVtSodu().add(khoanThu.getKtSotien());

        khoanThu.getVt().setVtSodu(tongTien);

    }

    private List<KhoanThu> findKhoanThuTheoThang(Integer viTienId, YearMonth thoiGian) {
        LocalDate startDate = thoiGian.atDay(1);
        LocalDate endDate = thoiGian.atEndOfMonth();

        return khoanThuRepository.findByVtAndThang(viTienId, startDate, endDate);
    }

    private List<ThongKeTheoThangResponse<?>> parseToThongKeTheoThangResponses(int viTienId, List<YearMonth> yearMonths) {
        List<ThongKeTheoThangResponse<?>> thongKeTheoThangResponses = new ArrayList<>();
        yearMonths.forEach(yearMonth -> {
            ThongKeTheoThangResponse<?> fullThang = (this.thongKeThuByViTienTheoThang(viTienId, yearMonth));
            if (fullThang != null) {
                ThongKeTheoThangResponse<Void> cleaned = ThongKeTheoThangResponse.<Void>builder()
                        .thoiGian(fullThang.getThoiGian())
                        .tongThu(fullThang.getTongThu())
                        .thuCaoNhat(fullThang.getThuCaoNhat())
                        .thuThapNhat(fullThang.getThuThapNhat())
                        .thuTrungBinh(fullThang.getThuTrungBinh())
                        .soGiaoDich(fullThang.getSoGiaoDich())
                        .thongKeTheoNgays(null)
                        .build();
                thongKeTheoThangResponses.add(cleaned);
            }
        });

        return thongKeTheoThangResponses;
    }

    private List<YearMonth> parseYearToYearMonths(Year thoiGian) {

        return IntStream.rangeClosed(1, 12)
                .mapToObj(month -> YearMonth.of(thoiGian.getValue(), month))
                .toList();
    }
}
