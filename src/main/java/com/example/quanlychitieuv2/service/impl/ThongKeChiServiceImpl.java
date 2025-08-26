package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.response.KhoanChiResponse;
import com.example.quanlychitieuv2.entity.KhoanChi;
import com.example.quanlychitieuv2.entity.ViTien;
import com.example.quanlychitieuv2.enums.LoaiGiaoDich;
import com.example.quanlychitieuv2.repository.KhoanChiRepository;
import com.example.quanlychitieuv2.service.AbstractThongKeService;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service xử lý các thao tác liên quan đến Khoản Chi
 * Cung cấp các phương thức CRUD và thống kê dữ liệu khoản chi theo ngày, tháng, năm
 */
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThongKeChiServiceImpl extends AbstractThongKeService<KhoanChi, KhoanChiResponse> {

    KhoanChiRepository khoanChiRepository;

    @Autowired
    public ThongKeChiServiceImpl(FindBy findBy, KhoanChiRepository khoanChiRepository) {
        super(findBy);
        this.khoanChiRepository = khoanChiRepository;
    }

    @Override
    public List<KhoanChi> getListByDay(ViTien viTien, LocalDate ngay_ngayDaydu) {
        return khoanChiRepository.findByVtAndNgayNgayDaydu(viTien, ngay_ngayDaydu);
    }

    @Override
    public Double getSoTien(KhoanChi item) {
        return item.getKcSotien().doubleValue();
    }

    @Override
    protected LoaiGiaoDich getLoaiGiaoDich() {
        return LoaiGiaoDich.CHI;
    }

    @Override
    protected List<KhoanChi> getListByMonth(Integer viTienId, LocalDate startDate, LocalDate endDate) {
        return khoanChiRepository.findByVtAndThang(viTienId, startDate, endDate);
    }

    @Override
    protected LocalDate getNgayDaydu(KhoanChi item) {
        return item.getNgay().getNgayDaydu();
    }

    @Override
    protected List<KhoanChiResponse> mapListToListRes(List<KhoanChi> items) {
        return List.of();
    }
}
