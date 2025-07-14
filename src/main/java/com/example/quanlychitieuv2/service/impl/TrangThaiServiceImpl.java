package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.TrangThaiRequest;
import com.example.quanlychitieuv2.dto.response.TrangThaiResponse;
import com.example.quanlychitieuv2.entity.TrangThai;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.TrangThaiMapper;
import com.example.quanlychitieuv2.repository.TrangThaiRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(makeFinal = true)
public class TrangThaiServiceImpl extends AbstractBaseService<TrangThaiRequest, TrangThaiResponse, TrangThai, Integer> {

    private TrangThaiMapper trangThaiMapper;

    public TrangThaiServiceImpl(TrangThaiRepository trangThaiRepository, TrangThaiMapper trangThaiMapper) {
        super(trangThaiRepository);
        this.trangThaiMapper = trangThaiMapper;
    }

    @Override
    protected BaseMapper<TrangThaiRequest, TrangThaiResponse, TrangThai> getMapper() {
        return trangThaiMapper;
    }
}
