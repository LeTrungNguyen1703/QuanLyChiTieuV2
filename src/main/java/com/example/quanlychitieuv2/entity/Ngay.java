package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Timer;

@Getter
@Setter
@Entity
@Table(name = "ngay")
public class Ngay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ngay_Id", nullable = false)
    private Integer id;

    @Column(name = "Ngay_DayDU", unique = true, nullable = false)
    private LocalDate ngayDaydu;

    @NotNull
    @Column(name = "Ngay_TrongTuan", nullable = false)
    private Integer ngayTrongtuan;

    @NotNull
    @Column(name = "Ngay_TrongThang", nullable = false)
    private Integer ngayTrongthang;

    @NotNull
    @Column(name = "Ngay_TrongNam", nullable = false)
    private Integer ngayTrongnam;

    @OneToMany(mappedBy = "ngay", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<HanMucChi> hanMucChis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ngay", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanChi> khoanChis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ngay", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanThu> khoanThus = new LinkedHashSet<>();

    public Ngay() {
        this.ngayDaydu = LocalDate.now();
        this.ngayTrongtuan = ngayDaydu.getDayOfWeek().getValue();
        this.ngayTrongthang = ngayDaydu.getDayOfMonth();
        this.ngayTrongnam = ngayDaydu.getDayOfYear();
    }

    @PrePersist
    public void prePersist() {
        if (ngayDaydu == null) {
            ngayDaydu = LocalDate.now();
        }
        // Tự động tính toán các trường từ ngayDaydu
        ngayTrongtuan = ngayDaydu.getDayOfWeek().getValue();
        ngayTrongthang = ngayDaydu.getDayOfMonth();
        ngayTrongnam = ngayDaydu.getDayOfYear();
    }

    @PreUpdate
    public void preUpdate() {
        if (ngayDaydu != null) {
            ngayTrongtuan = ngayDaydu.getDayOfWeek().getValue();
            ngayTrongthang = ngayDaydu.getDayOfMonth();
            ngayTrongnam = ngayDaydu.getDayOfYear();
        }
    }

}