package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNamResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNgayResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoThangResponse;
import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.enums.LoaiGiaoDich;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.KhoanThuMapper;
import com.example.quanlychitieuv2.repository.KhoanThuRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.service.ThongKeThuChiByViTien;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
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
public class KhoanThuServiceImpl extends AbstractBaseService<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> implements ThongKeThuChiByViTien {

    KhoanThuRepository khoanThuRepository;
    KhoanThuMapper khoanThuMapper;
    FindBy findBy;

    public KhoanThuServiceImpl(JpaRepository<KhoanThu, Integer> jpaRepository, KhoanThuRepository khoanThuRepository, KhoanThuMapper khoanThuMapper, FindBy findBy) {
        super(jpaRepository);
        this.khoanThuRepository = khoanThuRepository;
        this.khoanThuMapper = khoanThuMapper;
        this.findBy = findBy;
    }

    /**
     * Lấy đối tượng Mapper để chuyển đổi giữa các lớp DTO và Entity
     *
     * @return Mapper cho KhoanThu
     */
    @Override
    protected BaseMapper<KhoanThuRequest, KhoanThuResponse, KhoanThu> getMapper() {
        return khoanThuMapper;
    }

    /**
     * Tạo mới một khoản thu và cập nhật số dư ví tiền
     *
     * @param khoanThuRequest Request chứa thông tin khoản thu cần tạo
     * @return KhoanThuResponse chứa thông tin khoản thu đã tạo
     */
    @Override
    public KhoanThuResponse create(KhoanThuRequest khoanThuRequest) {
        KhoanThu khoanThu = khoanThuMapper.toEntity(khoanThuRequest);
        ViTien viTien = findBy.findViTienById(khoanThuRequest.getVtId());
        User user = this.getCurrentUserByName();
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
        List<KhoanThu> khoanThus = khoanThuRepository.findByVtAndNgayNgayDaydu(viTien, thoiGian);

        DoubleSummaryStatistics statistics = khoanThus.stream()
                .mapToDouble(khoanThu -> khoanThu.getKtSotien().doubleValue())
                .summaryStatistics();

        return ThongKeTheoNgayResponse.builder()
                .thoiGian(thoiGian.toString())
                .tongSoTien(statistics.getSum())
                .soTienCaoNhat(statistics.getMax())
                .soTienThapNhat(statistics.getMin())
                .soTienTrungBinh(statistics.getAverage())
                .soGiaoDich((int) statistics.getCount())
                .thongKeTheoThoiGianTrongNgay(this.mapListKhoanThuToKhoanThuResForThongKeTheoNgay(khoanThus))
                .loaiGiaoDich(LoaiGiaoDich.THU)
                .build();

    }


    /**
     * Thống kê khoản thu theo tháng cho một ví tiền cụ thể
     * Tìm tất cả khoản thu trong tháng, sau đó tính toán các chỉ số thống kê
     * và bao gồm cả thống kê chi tiết theo ngày
     *
     * @param viTienId ID của ví tiền cần thống kê
     * @param thoiGian Tháng cần thống kê (dạng YearMonth)
     * @return Báo cáo thống kê theo tháng hoặc null nếu không có khoản thu nào
     */
    @Override
    public ThongKeTheoThangResponse<?> thongKeByViTienTheoThang(int viTienId, YearMonth thoiGian) {
        findBy.findViTienById(viTienId);

        List<KhoanThu> khoanThus = this.findKhoanThuTheoThang(viTienId, thoiGian);
        if (khoanThus.isEmpty()) {
            return null;
        }

        List<ThongKeTheoNgayResponse> thongKeTheoNgays = this.xuLyThongKeTheoThang(khoanThus);

        DoubleSummaryStatistics statistics = khoanThus.stream()
                .mapToDouble(khoanThu -> khoanThu.getKtSotien().doubleValue())
                .summaryStatistics();


        return ThongKeTheoThangResponse.<ThongKeTheoNgayResponse>builder()
                .thoiGian(thoiGian.toString())
                .tongSoTien(statistics.getSum())
                .soTienCaoNhat(statistics.getMax())
                .soTienThapNhat(statistics.getMin())
                .soTienTrungBinh(statistics.getAverage())
                .soGiaoDich((int) statistics.getCount())
                .thongKeTheoNgays(thongKeTheoNgays)
                .loaiGiaoDich(LoaiGiaoDich.THU)
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

    /**
     * Tính toán và cập nhật số dư của ví tiền khi thêm một khoản thu mới
     * Số dư mới = Số dư cũ + Số tiền của khoản thu mới
     *
     * @param khoanThu Khoản thu cần tính toán và cập nhật số dư ví
     */
    private void tinhSoDu(KhoanThu khoanThu) {
        // Lấy số dư hiện tại và cộng thêm số tiền của khoản thu mới
        BigDecimal tongTien = khoanThu.getVt().getVtSodu().add(khoanThu.getKtSotien());

        // Cập nhật số dư mới cho ví tiền
        khoanThu.getVt().setVtSodu(tongTien);
    }

    /**
     * Tìm tất cả khoản thu theo ví tiền và tháng cụ thể
     *
     * @param viTienId ID của ví tiền cần tìm
     * @param thoiGian Tháng cần tìm (dạng YearMonth)
     * @return Danh sách các khoản thu thỏa mãn điều kiện
     */
    private List<KhoanThu> findKhoanThuTheoThang(Integer viTienId, YearMonth thoiGian) {
        // Xác định ngày đầu tiên và ngày cuối cùng của tháng
        LocalDate startDate = thoiGian.atDay(1);
        LocalDate endDate = thoiGian.atEndOfMonth();

        // Gọi repository để truy vấn dữ liệu
        return khoanThuRepository.findByVtAndThang(viTienId, startDate, endDate);
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

    private User getCurrentUserByName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            return findBy.findUserByName(authentication.getName());
        }
        return null;
    }

    private List<ThongKeTheoNgayResponse> xuLyThongKeTheoThang(List<KhoanThu> khoanThus) {

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

    private List<KhoanThuResponse> mapListKhoanThuToKhoanThuResForThongKeTheoNgay(List<KhoanThu> khoanThus) {
        return khoanThus.stream()
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
