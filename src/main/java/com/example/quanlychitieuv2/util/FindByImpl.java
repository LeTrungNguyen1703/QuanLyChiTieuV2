package com.example.quanlychitieuv2.util;

import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.enums.ErrorCode;
import com.example.quanlychitieuv2.exception.ResourceNotFound;
import com.example.quanlychitieuv2.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FindByImpl implements FindBy {
    LoaiViRepository loaiViRepository;
    ViTienRepository viTienRepository;
    PhuongThucThanhToanRepository phuongThucThanhToanRepository;
    UserRepository userRepository;
    LoaiKhoanThuRepository loaiKhoanThuRepository;
    KhoanChiRepository khoanChiRepository;
    KhoanThuRepository khoanThuRepository;
    HanMucChiRepository hanMucChiRepository;
    SoHuRepository soHuRepository;
    LoaiKhoanChiRepository loaiKhoanChiRepository;
    RoleRepository roleRepository;
    private final NgayRepository ngayRepository;


    @Override
    public LoaiKhoanChi findLoaiKhoanChiById(Integer id) {
        log.info("Tìm loại khoản chi với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            LoaiKhoanChi loaiKhoanChi = loaiKhoanChiRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy loại khoản chi: {}", loaiKhoanChi);
            return loaiKhoanChi;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy loại khoản chi với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm loại khoản chi với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public LoaiKhoanThu findLoaiKhoanThuById(Integer id) {
        log.info("Tìm loại khoản thu với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            LoaiKhoanThu loaiKhoanThu = loaiKhoanThuRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy loại khoản thu: {}", loaiKhoanThu);
            return loaiKhoanThu;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy loại khoản thu với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm loại khoản thu với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public HanMucChi findHanMucChiById(HanMucChiId id) {
        log.info("Tìm hạn mức chi với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            HanMucChi hanMucChi = hanMucChiRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy hạn mức chi: {}", hanMucChi);
            return hanMucChi;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy hạn mức chi với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm hạn mức chi với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public KhoanChi findKhoanChiById(Integer id) {
        log.info("Tìm khoản chi với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            KhoanChi khoanChi = khoanChiRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy khoản chi: {}", khoanChi);
            return khoanChi;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy khoản chi với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm khoản chi với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public KhoanThu findKhoanThuById(Integer id) {
        log.info("Tìm khoản thu với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            KhoanThu khoanThu = khoanThuRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy khoản thu: {}", khoanThu);
            return khoanThu;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy khoản thu với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm khoản thu với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public LoaiVi findLoaiViById(Integer id) {
        log.info("Tìm loại ví với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            LoaiVi loaiVi = loaiViRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy loại ví: {}", loaiVi);
            return loaiVi;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy loại ví với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm loại ví với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public User findNguoiDungById(Integer id) {
        log.info("Tìm người dùng với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            User nguoiDung = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy người dùng: {}", nguoiDung);
            return nguoiDung;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy người dùng với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm người dùng với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public PhuongThucThanhToan findPhuongThucThanhToanById(Integer id) {
        log.info("Tìm phương thức thanh toán với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            PhuongThucThanhToan phuongThucThanhToan = phuongThucThanhToanRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy phương thức thanh toán: {}", phuongThucThanhToan);
            return phuongThucThanhToan;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy phương thức thanh toán với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm phương thức thanh toán với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public SoHu findSoHuById(SoHuId id) {
        log.info("Tìm sở hữu với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            SoHu soHu = soHuRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy sở hữu: {}", soHu);
            return soHu;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy sở hữu với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm sở hữu với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public ViTien findViTienById(Integer id) {
        log.info("Tìm ví tiền với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            ViTien viTien = viTienRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy ví tiền: {}", viTien);
            return viTien;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy ví tiền với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm ví tiền với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public Role findRoleById(String id) {
        log.info("Tìm role với ID: {}", id);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + id;
        try {
            Role role = roleRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy role: {}", role);
            return role;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy role với ID: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm role với ID: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public User findUserByName(String username) {
        log.info("Tìm ndTen với userName: {}", username);
        String ErrorMessage = ErrorCode.NOT_FOUND.getMessage() + " với ID: " + username;
        try {
            User user = userRepository.findByNdTen(username)
                    .orElseThrow(() -> new ResourceNotFound(ErrorMessage));
            log.info("Đã tìm thấy ndTen: {}", username);
            return user;
        } catch (ResourceNotFound ex) {
            log.error("Không tìm thấy ndTen với ID: {}", username);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm ndTen với ID: {}", username, ex);
            throw ex;
        }
    }

    @Override
    public Ngay findNgayByNgayDayDu(LocalDate localDate) {
        log.info("Tìm ngày với ngày đầy đủ: {}", localDate);
        try {
        Ngay ngay = ngayRepository.findByNgayDaydu(localDate).orElse(null);
        if (ngay == null) {
                ngay = new Ngay();
                ngay.setNgayDaydu(localDate);
                ngay = ngayRepository.save(ngay);
                log.info("Đã tạo mới ngày: {}", ngay);
            } else {
                log.info("Đã tìm thấy ngày: {}", ngay);
        }
        return ngay;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm hoặc tạo ngày với ngày đầy đủ: {}", localDate, ex);
            throw ex;
        }
    }
}
