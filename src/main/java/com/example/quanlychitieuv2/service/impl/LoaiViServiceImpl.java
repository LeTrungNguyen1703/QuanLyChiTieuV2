package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.LoaiVi.LoaiViRequest;
import com.example.quanlychitieuv2.dto.response.LoaiViResponse;
import com.example.quanlychitieuv2.entity.LoaiVi;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.LoaiViMapper;
import com.example.quanlychitieuv2.repository.LoaiViRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiViServiceImpl extends AbstractBaseService<LoaiViRequest, LoaiViResponse, LoaiVi, Integer> {

    private LoaiViMapper loaiViMapper;

    public LoaiViServiceImpl(LoaiViRepository loaiViRepository, LoaiViMapper loaiViMapper) {
        super(loaiViRepository);
        this.loaiViMapper = loaiViMapper;
    }

    @Override
    protected BaseMapper<LoaiViRequest, LoaiViResponse, LoaiVi> getMapper() {
        return loaiViMapper;
    }
}
