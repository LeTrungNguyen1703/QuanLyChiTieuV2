package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNdTen(String ndTen);

    boolean existsByNdTen( String ndTen);
}