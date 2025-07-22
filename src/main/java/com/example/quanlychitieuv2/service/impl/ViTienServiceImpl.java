package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.ViTienDto;
import com.example.quanlychitieuv2.dto.response.ViTienResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.enums.TrangThaiHoatDong;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.ViTienMapper;
import com.example.quanlychitieuv2.repository.SoHuRepository;
import com.example.quanlychitieuv2.repository.ViTienRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ViTienServiceImpl extends AbstractBaseService<ViTienDto, ViTienResponse, ViTien, Integer> {
    ViTienMapper viTienMapper;
    FindBy findBy;
    ViTienRepository viTienRepository;
    SoHuRepository soHuRepository;

    @Autowired
    public ViTienServiceImpl(JpaRepository<ViTien, Integer> jpaRepository, ViTienMapper viTienMapper, FindBy findBy, ViTienRepository viTienRepository, SoHuRepository soHuRepository) {
        super(jpaRepository);
        this.viTienMapper = viTienMapper;
        this.findBy = findBy;
        this.viTienRepository = viTienRepository;
        this.soHuRepository = soHuRepository;
    }

    @Override
    protected BaseMapper<ViTienDto, ViTienResponse, ViTien> getMapper() {
        return this.viTienMapper;
    }

    @Override
    public ViTienResponse create(ViTienDto viTienDto) {

        User user = this.getUser();

        ViTien viTien = this.createViTien(viTienDto);

        SoHu soHu = SoHu.builder()
                .id(new SoHuId(user.getId(), viTien.getId()))
                .user(user)
                .vt(viTien)
                .build();
        soHuRepository.save(soHu);

        ViTienResponse viTienResponse = viTienMapper.toRes(viTien);
        viTienResponse.setNguoiDung(new ViTienResponse.UserResponse(user.getId(), user.getNdTen()));

        return viTienResponse;
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
