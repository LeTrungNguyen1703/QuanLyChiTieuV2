package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.ViTienDto;
import com.example.quanlychitieuv2.dto.response.ViTienResponse;
import com.example.quanlychitieuv2.entity.ViTien;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.ViTienMapper;
import com.example.quanlychitieuv2.repository.ViTienRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ViTienServiceImpl extends AbstractBaseService<ViTienDto, ViTienResponse, ViTien,Integer> {
    ViTienMapper viTienMapper;

    public ViTienServiceImpl(JpaRepository<ViTien, Integer> jpaRepository, ViTienMapper viTienMapper) {
        super(jpaRepository);
        this.viTienMapper = viTienMapper;
    }

    @Override
    protected BaseMapper<ViTienDto, ViTienResponse, ViTien> getMapper() {
        return this.viTienMapper;
    }

    @Override
    public List<ViTienResponse> findAll() {
        return super.findAll();
    }

    @Override
    public ViTienResponse findById(Integer integer) {
        return super.findById(integer);
    }

    @Override
    public ViTienResponse create(ViTienDto viTienDto) {
        return super.create(viTienDto);
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
