package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.LoaiVi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiViRepository extends JpaRepository<LoaiVi, Integer> {
}
