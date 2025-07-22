package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.Ngay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface NgayRepository extends JpaRepository<Ngay, Integer> {
    Optional<Ngay> findByNgayDaydu(LocalDate ngayDaydu);
}