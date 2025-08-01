package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.KhoanChiRequest;
import com.example.quanlychitieuv2.dto.response.KhoanChiResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNamResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoNgayResponse;
import com.example.quanlychitieuv2.dto.response.ThongKeThuChi.ThongKeTheoThangResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.enums.LoaiGiaoDich;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.KhoanChiMapper;
import com.example.quanlychitieuv2.repository.KhoanChiRepository;
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
public class KhoanChiServiceImpl extends AbstractBaseService<KhoanChiRequest, KhoanChiResponse, KhoanChi, Integer> implements ThongKeThuChiByViTien {

    KhoanChiMapper khoanChiMapper;
    FindBy findBy;
    private final KhoanChiRepository khoanChiRepository;

    public KhoanChiServiceImpl(JpaRepository<KhoanChi, Integer> jpaRepository, KhoanThuRepository khoanThuRepository, KhoanChiMapper khoanChiMapper, FindBy findBy, KhoanChiRepository khoanChiRepository) {
        super(jpaRepository);
        this.khoanChiMapper = khoanChiMapper;
        this.findBy = findBy;
        this.khoanChiRepository = khoanChiRepository;
    }

    /**
     * Lấy đối tượng Mapper để chuyển đổi giữa các lớp DTO và Entity
     *
     * @return Mapper cho KhoanThu
     */
    @Override
    protected BaseMapper<KhoanChiRequest, KhoanChiResponse, KhoanChi> getMapper() {
        return khoanChiMapper;
    }

    /**
     * Tạo mới một khoản chi và cập nhật số dư ví tiền
     *
     * @param khoanChiRequest Request chứa thông tin khoản thu cần tạo
     * @return khoanChiResponse chứa thông tin khoản chi đã tạo
     */
    @Override
    public KhoanChiResponse create(KhoanChiRequest khoanChiRequest) {
        log.info("Khoan chi request {}", khoanChiRequest);
        User user = this.getCurrentUserByName();
        ViTien viTien = findBy.findViTienById(khoanChiRequest.getVtId());
        Ngay ngay = new Ngay();
        ngay = findBy.findNgayByNgayDayDu(ngay.getNgayDaydu());
        LoaiKhoanChi loaiKhoanChi = findBy.findLoaiKhoanChiById(khoanChiRequest.getLkcId());
        PhuongThucThanhToan phuongThucThanhToan = findBy.findPhuongThucThanhToanById(khoanChiRequest.getPtttId());
        KhoanChi khoanChi = khoanChiMapper.toEntity(khoanChiRequest);

        khoanChi.setUser(user);
        khoanChi.setVt(viTien);
        khoanChi.setNgay(ngay);
        khoanChi.setLkc(loaiKhoanChi);
        khoanChi.setPttt(phuongThucThanhToan);
        this.tinhSoDu(khoanChi);

        log.info("Khoan chi sau khi map {}", khoanChi);

        khoanChi = jpaRepository.save(khoanChi);

        return khoanChiMapper.toRes(khoanChi);
    }

    /**
     * Thống kê khoản chi theo ngày cho một danh sách các khoản chi
     * Gom nhóm các khoản chi theo ngày và tính toán các chỉ số thống kê
     *
     * @param khoanChis Danh sách các khoản chi cần thống kê
     * @return Danh sách các báo cáo thống kê theo ngày, đã được sắp xếp theo thời gian
     */
    public List<ThongKeTheoNgayResponse> thongKeChiByViTienTheoNgay(List<KhoanChi> khoanChis) {

        Map<LocalDate, List<KhoanChi>> groupedByDate = khoanChis.stream()
                .collect(Collectors.groupingBy(khoanChi -> khoanChi.getNgay().getNgayDaydu()));

        return groupedByDate.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<KhoanChi> listInDay = entry.getValue();

                    DoubleSummaryStatistics doubleSummaryStatistics = listInDay.stream()
                            .mapToDouble(kc -> kc.getKcSotien().doubleValue())
                            .summaryStatistics();

                    return ThongKeTheoNgayResponse.builder()
                            .tongSoTien(doubleSummaryStatistics.getSum())
                            .soTienCaoNhat(doubleSummaryStatistics.getMax())
                            .soTienTrungBinh(doubleSummaryStatistics.getAverage())
                            .soTienThapNhat(doubleSummaryStatistics.getMin())
                            .soGiaoDich((int) doubleSummaryStatistics.getCount())
                            .thoiGian(date.toString())
                            .loaiGiaoDich(LoaiGiaoDich.CHI)
                            .build();

                })
                .sorted(Comparator.comparing(ThongKeTheoNgayResponse::getThoiGian)) // Sắp xếp theo ngày tăng dần
                .collect(Collectors.toList());
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

        List<KhoanChi> khoanChis = this.findKhoanChiTheoThang(viTienId, thoiGian);
        if (khoanChis.isEmpty()) {
            return null;
        }

        List<ThongKeTheoNgayResponse> thongKeTheoNgays = this.thongKeChiByViTienTheoNgay(khoanChis);

        thongKeTheoNgays.forEach(thongKeTheoNgay -> {
            thongKeTheoNgay.setLoaiGiaoDich(null);
        });

        DoubleSummaryStatistics statistics = khoanChis.stream()
                .mapToDouble(khoanChi -> khoanChi.getKcSotien().doubleValue())
                .summaryStatistics();


        return ThongKeTheoThangResponse.<ThongKeTheoNgayResponse>builder()
                .thoiGian(thoiGian.toString())
                .tongSoTien(statistics.getSum())
                .soTienCaoNhat(statistics.getMax())
                .soTienThapNhat(statistics.getMin())
                .soTienTrungBinh(statistics.getAverage())
                .soGiaoDich((int) statistics.getCount())
                .thongKeTheoNgays(thongKeTheoNgays)
                .loaiGiaoDich(LoaiGiaoDich.CHI)
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
                .loaiGiaoDich(LoaiGiaoDich.CHI)
                .build();
    }

    /**
     * Tính toán và cập nhật số dư của ví tiền khi thêm một khoản chi mới
     * Số dư mới = Số dư cũ - Số tiền của khoản chi mới
     *
     * @param khoanChi Khoản chi cần tính toán và cập nhật số dư ví
     */
    private void tinhSoDu(KhoanChi khoanChi) {
        // Lấy số dư hiện tại và trừ thêm số tiền của khoản thu mới
        BigDecimal tongTien = khoanChi.getVt().getVtSodu().subtract(khoanChi.getKcSotien());

        // Cập nhật số dư mới cho ví tiền
        khoanChi.getVt().setVtSodu(tongTien);
    }

    /**
     * Tìm tất cả khoản thu theo ví tiền và tháng cụ thể
     *
     * @param viTienId ID của ví tiền cần tìm
     * @param thoiGian Tháng cần tìm (dạng YearMonth)
     * @return Danh sách các khoản thu thỏa mãn điều kiện
     */
    private List<KhoanChi> findKhoanChiTheoThang(Integer viTienId, YearMonth thoiGian) {
        // Xác định ngày đầu tiên và ngày cuối cùng của tháng
        LocalDate startDate = thoiGian.atDay(1);
        LocalDate endDate = thoiGian.atEndOfMonth();

        // Gọi repository để truy vấn dữ liệu
        return khoanChiRepository.findByVtAndThang(viTienId, startDate, endDate);
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
}
