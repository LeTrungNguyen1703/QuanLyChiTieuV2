package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.ChucNangRequest;
import com.example.quanlychitieuv2.dto.response.ChucNangResponse;
import com.example.quanlychitieuv2.entity.ChucNang;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.ChucNangMapper;
import com.example.quanlychitieuv2.repository.ChucNangRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChucNangServiceImpl extends AbstractBaseService<ChucNangRequest, ChucNangResponse, ChucNang, Integer> {

    ChucNangMapper chucNangMapper;

    public ChucNangServiceImpl(ChucNangRepository chucNangRepository, ChucNangMapper chucNangMapper) {
        super(chucNangRepository);
        this.chucNangMapper = chucNangMapper;
    }

    @Override
    protected BaseMapper<ChucNangRequest, ChucNangResponse, ChucNang> getMapper() {
        return chucNangMapper;
    }
}
