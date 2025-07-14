package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.ChucNang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucNangRepository extends JpaRepository<ChucNang, Integer> {
}
