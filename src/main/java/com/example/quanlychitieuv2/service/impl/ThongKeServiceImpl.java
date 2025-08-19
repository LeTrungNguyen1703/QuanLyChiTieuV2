package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNamResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNgayResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoThangResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.enums.LoaiGiaoDich;
import com.example.quanlychitieuv2.service.ThongKeThuChiByViTien;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public abstract class ThongKeServiceImpl<Entity,Res> implements ThongKeThuChiByViTien {
    
    FindBy findBy;
    
    @Autowired
    public ThongKeServiceImpl(FindBy findBy) {
        this.findBy = findBy;
    }
    
    // Abstract methods grouped together
    public abstract List<Entity> getListByDay(ViTien viTien, LocalDate ngay_ngayDaydu);
    public abstract Double getSoTien(Entity item);
    protected abstract LoaiGiaoDich getLoaiGiaoDich();
    protected abstract List<Entity> getListByMonth(Integer viTienId, LocalDate startDate, LocalDate endDate);
    protected abstract LocalDate getNgayDaydu(Entity item);
    protected abstract List<Res> mapListToListRes(List<Entity> item);

    /**
     * Thống kê khoản thu theo ngày cho một danh sách các khoản thu
     * Gom nhóm các khoản thu theo ngày và tính toán các chỉ số thống kê
     *
     * @param viTienId ví tiền cần thống kê
     * @param thoiGian thời gian trong ngày format: yyyy-mm-dd
     * @return Danh sách các báo cáo thống kê theo ngày, đã được sắp xếp theo thời gian
     */

    @Override
    public ThongKeTheoNgayResponse<?> thongKeTheoNgay(int viTienId, LocalDate thoiGian) {
        ViTien viTien = findBy.findViTienById(viTienId);
        List<Entity> lists = getListByDay(viTien, thoiGian);

        DoubleSummaryStatistics statistics = lists.stream()
                .mapToDouble(this::getSoTien)
                .summaryStatistics();

        return ThongKeTheoNgayResponse.builder()
                .thoiGian(thoiGian.toString())
                .tongSoTien(statistics.getSum())
                .soTienCaoNhat(statistics.getMax())
                .soTienThapNhat(statistics.getMin())
                .soTienTrungBinh(statistics.getAverage())
                .soGiaoDich((int) statistics.getCount())
                .thongKeTheoThoiGianTrongNgay(this.mapListToListRes(lists))
                .loaiGiaoDich(getLoaiGiaoDich())
                .build();

    }
    
    
    @Override
    public ThongKeTheoThangResponse<?> thongKeByViTienTheoThang(int viTienId, YearMonth thoiGian) {
        findBy.findViTienById(viTienId);

        List<Entity> lists = this.findKhoanThuTheoThang(viTienId, thoiGian);
        if (lists.isEmpty()) {
            return null;
        }

        List<ThongKeTheoNgayResponse> thongKeTheoNgays = this.xuLyThongKeTheoThang(lists);

        DoubleSummaryStatistics statistics = lists.stream()
                .mapToDouble(this::getSoTien)
                .summaryStatistics();

        return ThongKeTheoThangResponse.<ThongKeTheoNgayResponse>builder()
                .thoiGian(thoiGian.toString())
                .tongSoTien(statistics.getSum())
                .soTienCaoNhat(statistics.getMax())
                .soTienThapNhat(statistics.getMin())
                .soTienTrungBinh(statistics.getAverage())
                .soGiaoDich((int) statistics.getCount())
                .thongKeTheoNgays(thongKeTheoNgays)
                .loaiGiaoDich(getLoaiGiaoDich())
                .build();
    }

    /**
     * Thống kê khoản thu theo năm cho một ví tiền cụ thể
     * Phân tích năm thành 12 tháng, thống kê từng tháng và tổng hợp kết quả
     *
     * @param viTienId ID của ví tiền cần thống kê
     * @param thoiGian Năm cần thống kê (dạng Year)
     * @return Báo cáo thống kê theo năm bao gồm các thống kê theo tháng
     */
    @Override
    public ThongKeTheoNamResponse<?> thongKeByViTienTheoNam(int viTienId, Year thoiGian) {

        // Chuyển đổi năm thành danh sách 12 tháng trong năm
        List<YearMonth> yearMonths = this.parseYearToYearMonths(thoiGian);

        // Lấy thống kê cho mỗi tháng và lọc ra các tháng có dữ liệu
        List<ThongKeTheoThangResponse<?>> thongKeTheoThangResponses = this.parseToThongKeTheoThangResponses(viTienId, yearMonths);

        // Tính toán thống kê tổng hợp cho cả năm
        DoubleSummaryStatistics doubleSummaryStatistics = thongKeTheoThangResponses.stream()
                .mapToDouble(ThongKeTheoThangResponse::getTongSoTien)
                .summaryStatistics();

        return ThongKeTheoNamResponse.<ThongKeTheoThangResponse<?>>builder()
                .thoiGian(thoiGian.toString())
                .tongSoTien(doubleSummaryStatistics.getSum())
                .soTienCaoNhat(doubleSummaryStatistics.getMax())
                .soTienTrungBinh(doubleSummaryStatistics.getAverage())
                .soTienThapNhat(doubleSummaryStatistics.getMin())
                .thongKeTheoThangs(thongKeTheoThangResponses)
                .loaiGiaoDich(LoaiGiaoDich.THU)
                .build();
    }

    private List<Entity> findKhoanThuTheoThang(Integer viTienId, YearMonth thoiGian) {
        // Xác định ngày đầu tiên và ngày cuối cùng của tháng
        LocalDate startDate = thoiGian.atDay(1);
        LocalDate endDate = thoiGian.atEndOfMonth();

        // Gọi repository để truy vấn dữ liệu
        return getListByMonth(viTienId, startDate, endDate);
    }

    /**
     * Chuyển đổi danh sách các tháng thành danh sách các báo cáo thống kê theo tháng
     * Xóa thông tin chi tiết theo ngày để giảm kích thước dữ liệu
     *
     * @param viTienId   ID của ví tiền cần thống kê
     * @param yearMonths Danh sách các tháng cần thống kê
     * @return Danh sách các báo cáo thống kê theo tháng đã được làm gọn
     */
    private List<ThongKeTheoThangResponse<?>> parseToThongKeTheoThangResponses(int viTienId, List<YearMonth> yearMonths) {
        List<ThongKeTheoThangResponse<?>> thongKeTheoThangResponses = new ArrayList<>();
        yearMonths.forEach(yearMonth -> {
            // Lấy thông tin thống kê đầy đủ của một tháng
            ThongKeTheoThangResponse<?> fullThang = (this.thongKeByViTienTheoThang(viTienId, yearMonth));
            if (fullThang != null) {
                // Chỉ giữ lại thông tin tổng hợp, loại bỏ chi tiết theo ngày để giảm kích thước dữ liệu
                ThongKeTheoThangResponse<Void> cleaned = ThongKeTheoThangResponse.<Void>builder()
                        .thoiGian(fullThang.getThoiGian())
                        .tongSoTien(fullThang.getTongSoTien())
                        .soTienCaoNhat(fullThang.getSoTienCaoNhat())
                        .soTienThapNhat(fullThang.getSoTienThapNhat())
                        .soTienTrungBinh(fullThang.getSoTienTrungBinh())
                        .soGiaoDich(fullThang.getSoGiaoDich())
                        .thongKeTheoNgays(null) // Loại bỏ dữ liệu chi tiết theo ngày
                        .build();
                thongKeTheoThangResponses.add(cleaned);
            }
        });

        return thongKeTheoThangResponses;
    }

    /**
     * Chuyển đổi năm thành danh sách 12 tháng trong năm đó
     *
     * @param thoiGian Năm cần chuyển đổi
     * @return Danh sách 12 tháng trong năm
     */
    private List<YearMonth> parseYearToYearMonths(Year thoiGian) {
        // Tạo danh sách các tháng từ 1 đến 12 của năm
        return IntStream.rangeClosed(1, 12)
                .mapToObj(month -> YearMonth.of(thoiGian.getValue(), month))
                .toList();
    }

    private List<ThongKeTheoNgayResponse> xuLyThongKeTheoThang(List<Entity> lists) {

        Map<LocalDate, List<Entity>> groupedByDate = lists.stream()
                .collect(Collectors.groupingBy(this::getNgayDaydu));

        return groupedByDate.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<Entity> listInDay = entry.getValue();

                    DoubleSummaryStatistics doubleSummaryStatistics = listInDay.stream()
                            .mapToDouble(this::getSoTien)
                            .summaryStatistics();

                    return ThongKeTheoNgayResponse.builder()
                            .tongSoTien(doubleSummaryStatistics.getSum())
                            .soTienCaoNhat(doubleSummaryStatistics.getMax())
                            .soTienTrungBinh(doubleSummaryStatistics.getAverage())
                            .soTienThapNhat(doubleSummaryStatistics.getMin())
                            .soGiaoDich((int) doubleSummaryStatistics.getCount())
                            .thoiGian(date.toString())
                            .loaiGiaoDich(LoaiGiaoDich.THU)
                            .build();

                })
                .sorted(Comparator.comparing(ThongKeTheoNgayResponse::getThoiGian)) // Sắp xếp theo ngày tăng dần
                .collect(Collectors.toList());
    }

   
}
