package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NguoiDungRepository extends JpaRepository<User, Integer> {
}