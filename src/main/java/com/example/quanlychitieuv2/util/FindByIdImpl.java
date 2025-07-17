package com.example.quanlychitieuv2.util;

import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.exception.ResourceNotFound;
import com.example.quanlychitieuv2.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindByIdImpl implements FindById {
    LoaiViRepository loaiViRepository;
    ViTienRepository viTienRepository;
    PhuongThucThanhToanRepository phuongThucThanhToanRepository;
    NguoiDungRepository nguoiDungRepository;
    LoaiKhoanThuRepository loaiKhoanThuRepository;
    KhoanChiRepository khoanChiRepository;
    KhoanThuRepository khoanThuRepository;
    ChucNangRepository chucNangRepository;
    HanMucChiRepository hanMucChiRepository;
    PhanQuyenRepository phanQuyenRepository;
    QuyenTruyCapRepository quyenTruyCapRepository;
    SoHuRepository soHuRepository;
    LoaiKhoanChiRepository loaiKhoanChiRepository;

    @Override
    public LoaiKhoanChi findLoaiKhoanChiById(Integer id) {
        return loaiKhoanChiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Loại khoản chi không tồn tại với ID: " + id));
    }

    @Override
    public LoaiKhoanThu findLoaiKhoanThuById(Integer id) {
        return loaiKhoanThuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Loại khoản thu không tồn tại với ID: " + id));
    }

    @Override
    public ChucNang findChucNangById(Integer id) {
        return chucNangRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Chức năng không tồn tại với ID: " + id));
    }

    @Override
    public HanMucChi findHanMucChiById(HanMucChiId id) {
        return hanMucChiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Hạn mức chi không tồn tại với ID: " + id));
    }

    @Override
    public KhoanChi findKhoanChiById(Integer id) {
        return khoanChiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Khoản chi không tồn tại với ID: " + id));
    }

    @Override
    public KhoanThu findKhoanThuById(Integer id) {
        return khoanThuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Khoản thu không tồn tại với ID: " + id));
    }

    @Override
    public LoaiVi findLoaiViById(Integer id) {
        return loaiViRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Loại ví không tồn tại với ID: " + id));
    }

    @Override
    public User findNguoiDungById(Integer id) {
        return nguoiDungRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Người dùng không tồn tại với ID: " + id));
    }

    @Override
    public PhanQuyen findPhanQuyenById(Integer id) {
        return phanQuyenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Phân quyền không tồn tại với ID: " + id));
    }

    @Override
    public PhuongThucThanhToan findPhuongThucThanhToanById(Integer id) {
        return phuongThucThanhToanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Phương thức thanh toán không tồn tại với ID: " + id));
    }

    @Override
    public QuyenTruyCap findQuyenTruyCapById(Integer id) {
        return quyenTruyCapRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Quyền truy cập không tồn tại với ID: " + id));
    }

    @Override
    public SoHu findSoHuById(SoHuId id) {
        return soHuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Sở hữu không tồn tại với ID: " + id));
    }

    @Override
    public ViTien findViTienById(Integer id) {
        return viTienRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Ví tiền không tồn tại với ID: " + id));
    }
}
