package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.HanMucChi;
import com.example.quanlychitieuv2.entity.HanMucChiId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HanMucChiRepository extends JpaRepository<HanMucChi, HanMucChiId> {
}