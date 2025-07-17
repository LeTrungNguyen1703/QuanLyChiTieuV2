package com.example.quanlychitieuv2.util;

import com.example.quanlychitieuv2.entity.*;

public interface FindById {

    LoaiKhoanChi findLoaiKhoanChiById(Integer id);
    LoaiKhoanThu  findLoaiKhoanThuById(Integer id);
    ChucNang findChucNangById(Integer id);
    HanMucChi findHanMucChiById(HanMucChiId id);
    KhoanChi findKhoanChiById(Integer id);
    KhoanThu findKhoanThuById(Integer id);
    LoaiVi findLoaiViById(Integer id);
    User findNguoiDungById(Integer id);
    PhanQuyen  findPhanQuyenById(Integer id);
    PhuongThucThanhToan findPhuongThucThanhToanById(Integer id);
    QuyenTruyCap findQuyenTruyCapById(Integer id);
    SoHu findSoHuById(SoHuId id);
    ViTien findViTienById(Integer id);
}
