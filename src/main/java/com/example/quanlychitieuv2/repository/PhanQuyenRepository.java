package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.PhanQuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhanQuyenRepository extends JpaRepository<PhanQuyen, Integer> {
}
