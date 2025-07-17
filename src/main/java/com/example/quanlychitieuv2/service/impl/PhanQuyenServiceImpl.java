package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.response.PhanQuyenResponse;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.PhanQuyenMapper;
import com.example.quanlychitieuv2.repository.PhanQuyenRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PhanQuyenServiceImpl extends AbstractBaseService<PhanQuyenRequest, PhanQuyenResponse, PhanQuyen, Integer> {

    private PhanQuyenMapper phanQuyenMapper;

    public PhanQuyenServiceImpl(PhanQuyenRepository phanQuyenRepository, PhanQuyenMapper phanQuyenMapper) {
        super(phanQuyenRepository);
        this.phanQuyenMapper = phanQuyenMapper;
    }

    @Override
    protected BaseMapper<PhanQuyenRequest, PhanQuyenResponse, PhanQuyen> getMapper() {
        return phanQuyenMapper;
    }
}
