package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.ViTienDto;
import com.example.quanlychitieuv2.dto.response.ViTienResponse;
import com.example.quanlychitieuv2.entity.LoaiVi;
import com.example.quanlychitieuv2.entity.TrangThai;
import com.example.quanlychitieuv2.entity.ViTien;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.ViTienMapper;
import com.example.quanlychitieuv2.repository.LoaiViRepository;
import com.example.quanlychitieuv2.repository.ViTienRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.util.FindById;
import com.example.quanlychitieuv2.util.FindByIdImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ViTienServiceImpl extends AbstractBaseService<ViTienDto, ViTienResponse, ViTien,Integer> {
    ViTienMapper viTienMapper;
    LoaiViRepository loaiViRepository;
    FindById findById;
    ViTienRepository viTienRepository;

    @Autowired
    public ViTienServiceImpl(JpaRepository<ViTien, Integer> jpaRepository, ViTienMapper viTienMapper, LoaiViRepository loaiViRepository, FindById findById, ViTienRepository viTienRepository) {
        super(jpaRepository);
        this.viTienMapper = viTienMapper;
        this.loaiViRepository = loaiViRepository;
        this.findById = findById;
        this.viTienRepository = viTienRepository;
    }

    @Override
    protected BaseMapper<ViTienDto, ViTienResponse, ViTien> getMapper() {
        return this.viTienMapper;
    }

    @Override
    public ViTienResponse create(ViTienDto viTienDto) {
        TrangThai trangThai = new TrangThai();
        LoaiVi loaiVi = findById.findLoaiViById(viTienDto.getLvId());
        ViTien viTien = viTienMapper.toEntity(viTienDto);
        viTien.setLv(loaiVi);
        viTien.setTt(trangThai);
        viTien = viTienRepository.save(viTien);

        return viTienMapper.toRes(viTien);
    }

    @Override
    public void update(Integer integer, ViTienDto viTienDto) {
        super.update(integer, viTienDto);
    }

    @Override
    public void deleteById(Integer integer) {
        super.deleteById(integer);
    }
}
