package com.example.quanlychitieuv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuanLyChiTieuV2Application {

    public static void main(String[] args) {
        SpringApplication.run(QuanLyChiTieuV2Application.class, args);
    }

}
