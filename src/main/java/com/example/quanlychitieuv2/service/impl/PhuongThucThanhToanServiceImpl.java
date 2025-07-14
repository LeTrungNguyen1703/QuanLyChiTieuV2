package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.PhuongThucThanhToanRequest;
import com.example.quanlychitieuv2.dto.response.PhuongThucThanhToanResponse;
import com.example.quanlychitieuv2.entity.PhuongThucThanhToan;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.PhuongThucThanhToanMapper;
import com.example.quanlychitieuv2.repository.PhuongThucThanhToanRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(makeFinal = true)
public class PhuongThucThanhToanServiceImpl extends AbstractBaseService<PhuongThucThanhToanRequest, PhuongThucThanhToanResponse, PhuongThucThanhToan, Integer> {
    private PhuongThucThanhToanMapper phuongThucThanhToanMapper;

    public PhuongThucThanhToanServiceImpl(JpaRepository<PhuongThucThanhToan, Integer> jpaRepository, PhuongThucThanhToanMapper phuongThucThanhToanMapper) {
        super(jpaRepository);
        this.phuongThucThanhToanMapper = phuongThucThanhToanMapper;
    }

    @Override
    protected BaseMapper<PhuongThucThanhToanRequest, PhuongThucThanhToanResponse, PhuongThucThanhToan> getMapper() {
        return phuongThucThanhToanMapper;
    }
}
