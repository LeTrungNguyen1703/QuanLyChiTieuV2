package com.example.quanlychitieuv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucNangRepository extends JpaRepository<ChucNang, Integer> {
}
