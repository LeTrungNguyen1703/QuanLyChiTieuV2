package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}