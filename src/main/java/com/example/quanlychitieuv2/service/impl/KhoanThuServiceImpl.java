package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.KhoanThuMapper;
import com.example.quanlychitieuv2.repository.KhoanThuRepository;
import com.example.quanlychitieuv2.repository.LoaiKhoanThuRepository;
import com.example.quanlychitieuv2.repository.ViTienRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.util.FindBy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhoanThuServiceImpl extends AbstractBaseService<KhoanThuRequest, KhoanThuResponse, KhoanThu, Integer> {

    KhoanThuRepository khoanThuRepository;
    KhoanThuMapper khoanThuMapper;
    FindBy findBy;

    public KhoanThuServiceImpl(JpaRepository<KhoanThu, Integer> jpaRepository, KhoanThuRepository khoanThuRepository, KhoanThuMapper khoanThuMapper, FindBy findBy) {
        super(jpaRepository);
        this.khoanThuRepository = khoanThuRepository;
        this.khoanThuMapper = khoanThuMapper;
        this.findBy = findBy;
    }

    @Override
    protected BaseMapper<KhoanThuRequest, KhoanThuResponse, KhoanThu> getMapper() {
        return khoanThuMapper;
    }

    @Override
    public KhoanThuResponse create(KhoanThuRequest khoanThuRequest) {
        KhoanThu khoanThu = khoanThuMapper.toEntity(khoanThuRequest);
        ViTien viTien = findBy.findViTienById(khoanThuRequest.getVtId());
        User user = findBy.findNguoiDungById(khoanThuRequest.getUserId());
        LoaiKhoanThu loaiKhoanThu = findBy.findLoaiKhoanThuById(khoanThuRequest.getLktId());
        Ngay ngay = new  Ngay();
        ngay = findBy.findNgayByNgayDayDu(ngay.getNgayDaydu());

        khoanThu.setVt(viTien);
        khoanThu.setUser(user);
        khoanThu.setLkt(loaiKhoanThu);
        khoanThu.setNgay(ngay);

        this.tinhSoDu(khoanThu);

        khoanThu = khoanThuRepository.save(khoanThu);

        return khoanThuMapper.toRes(khoanThu);
    }

    private void tinhSoDu(KhoanThu khoanThu) {

        BigDecimal tongTien = khoanThu.getVt().getVtSodu().add(khoanThu.getKtSotien());

        khoanThu.getVt().setVtSodu(tongTien);

    }
}
