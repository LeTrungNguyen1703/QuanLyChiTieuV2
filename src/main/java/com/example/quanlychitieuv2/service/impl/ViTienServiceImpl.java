package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.ViTienDto;
import com.example.quanlychitieuv2.dto.response.ViTienResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.enums.TrangThaiHoatDong;
import com.example.quanlychitieuv2.exception.ResourceNotFound;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.ViTienMapper;
import com.example.quanlychitieuv2.repository.RoleRepository;
import com.example.quanlychitieuv2.repository.SoHuRepository;
import com.example.quanlychitieuv2.repository.ViTienRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.AccessLevel;
import lombok.extern.slf4j.Slf4j;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ViTienServiceImpl extends AbstractBaseService<ViTienDto, ViTienResponse, ViTien, Integer> {
    ViTienMapper viTienMapper;
    FindBy findBy;
    ViTienRepository viTienRepository;
    SoHuRepository soHuRepository;
    RoleRepository roleRepository;

    @Autowired
    public ViTienServiceImpl(JpaRepository<ViTien, Integer> jpaRepository, ViTienMapper viTienMapper,
                             FindBy findBy, ViTienRepository viTienRepository, SoHuRepository soHuRepository,
                             RoleRepository roleRepository) {
        super(jpaRepository);
        this.viTienMapper = viTienMapper;
        this.findBy = findBy;
        this.viTienRepository = viTienRepository;
        this.soHuRepository = soHuRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    protected BaseMapper<ViTienDto, ViTienResponse, ViTien> getMapper() {
        return this.viTienMapper;
    }

    /**
     * Tạo mới một ví tiền và thiết lập quyền sở hữu
     * <p>
     * Phương thức này thực hiện các bước sau:
     * 1. Lấy thông tin người dùng hiện tại từ context bảo mật
     * 2. Tạo mới đối tượng ví tiền từ DTO đầu vào
     * 3. Thiết lập quyền OWNER cho người dùng hiện tại đối với ví tiền mới
     * 4. Lưu thông tin sở hữu vào bảng SoHu
     * 5. Chuyển đổi và trả về đối tượng response
     *
     * @param viTienDto Đối tượng DTO chứa thông tin ví tiền cần tạo
     * @return ViTienResponse Đối tượng response chứa thông tin ví tiền đã tạo
     * @throws ResourceNotFound Khi không tìm thấy vai trò OWNER trong hệ thống
     */
    @Override
    @Transactional
    public ViTienResponse create(ViTienDto viTienDto) {
        log.info("Bắt đầu tạo ví tiền mới với thông tin: {}", viTienDto);

        // Lấy thông tin người dùng hiện tại
        User user = this.getUser();
        log.info("Người dùng thực hiện tạo ví: {} (ID: {})", user.getNdTen(), user.getId());

        // Tạo và lưu ví tiền mới
        ViTien viTien = this.createViTien(viTienDto);
        log.info("Đã tạo ví tiền mới với ID: {}", viTien.getId());

        // Lấy quyền OWNER mặc định cho chủ ví tiền
        Role ownerRole = roleRepository.findById("OWNER")
                .orElseThrow(() -> {
                    log.error("Không tìm thấy vai trò OWNER trong hệ thống");
                    return new ResourceNotFound("Không tìm thấy vai trò OWNER");
                });
        log.info("Đã lấy vai trò OWNER: {}", ownerRole.getName());

        // Kiểm tra để đảm bảo các giá trị đều không null trước khi tạo SoHuId
        if (user.getId() == null || viTien.getId() == null || ownerRole.getName() == null) {
            log.error("Lỗi: Thiếu thông tin cần thiết để tạo SoHuId - userId: {}, viTienId: {}, roleName={}",
                    user.getId(), viTien.getId(), ownerRole.getName());
            throw new IllegalStateException("Không thể tạo SoHuId do thiếu thông tin cần thiết");
        }

        // Tạo SoHuId với đầy đủ thông tin
        SoHuId soHuId = new SoHuId(user.getId(), viTien.getId(), ownerRole.getName());
        log.info("Đã tạo SoHuId: userId={}, viTienId={}, roleName={}",
                soHuId.getUserDuocCapId(), soHuId.getVtId(), soHuId.getRoleId());

        // Tạo đối tượng SoHu với đầy đủ thông tin quan hệ
        SoHu soHu = SoHu.builder()
                .id(soHuId)
                .userDuocCapQuyenId(user) // Người được cấp quyền chính là người tạo ví
                .userCapQuyenId(null) // null vì người dùng là chủ sở hữu
                .vt(viTien)
                .role(ownerRole)
                .build();

        // Lưu thông tin sở hữu
        SoHu savedSoHu = soHuRepository.save(soHu);
        log.info("Đã lưu thông tin sở hữu với roleId: {}", savedSoHu.getRole().getName());
        // Trả về thông tin ví tiền
        var soHus = new HashSet<SoHu>();
        soHus.add(savedSoHu);
        viTien.setSoHus(soHus);
        return viTienMapper.toRes(viTien);
    }

    @Override
    public void update(Integer id, ViTienDto viTienDto) {
        ViTien viTien = findBy.findViTienById(id);
        if (viTienDto.getLvId() != null) {
            LoaiVi loaiVi = findBy.findLoaiViById(viTienDto.getLvId());
            viTien.setLv(loaiVi);
        }

        viTienMapper.updateEntity(viTienDto, viTien);
        viTienRepository.save(viTien);
    }

    private User getUser() {
        String ndTen = SecurityContextHolder.getContext().getAuthentication().getName();
        return findBy.findUserByName(ndTen);
    }

    private ViTien createViTien(ViTienDto viTienDto) {
        LoaiVi loaiVi = findBy.findLoaiViById(viTienDto.getLvId());

        ViTien viTien = viTienMapper.toEntity(viTienDto);
        viTien.setLv(loaiVi);
        viTien.setTrangThaiHoatDong(TrangThaiHoatDong.BAT);

        viTien = viTienRepository.save(viTien);

        return viTien;
    }

}
