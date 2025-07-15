package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.SoHu;
import com.example.quanlychitieuv2.entity.SoHuId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoHuRepository extends JpaRepository<SoHu, SoHuId> {
}