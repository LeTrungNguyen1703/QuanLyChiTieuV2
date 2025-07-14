package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiRepository extends JpaRepository<TrangThai, Integer> {
}
