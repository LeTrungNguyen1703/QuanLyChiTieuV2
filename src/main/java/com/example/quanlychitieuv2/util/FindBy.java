package com.example.quanlychitieuv2.util;

import com.example.quanlychitieuv2.entity.*;

public interface FindBy {

    LoaiKhoanChi findLoaiKhoanChiById(Integer id);
    LoaiKhoanThu  findLoaiKhoanThuById(Integer id);
    HanMucChi findHanMucChiById(HanMucChiId id);
    KhoanChi findKhoanChiById(Integer id);
    KhoanThu findKhoanThuById(Integer id);
    LoaiVi findLoaiViById(Integer id);
    User findNguoiDungById(Integer id);
    PhuongThucThanhToan findPhuongThucThanhToanById(Integer id);
    SoHu findSoHuById(SoHuId id);
    ViTien findViTienById(Integer id);
    Permission findPermissionById(String id);
    Role findRoleById(String id);
    User findUserByName(String username);
}
