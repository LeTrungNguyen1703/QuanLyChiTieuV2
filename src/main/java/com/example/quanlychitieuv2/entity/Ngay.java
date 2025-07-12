package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ngay")
public class Ngay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ngay_Id", nullable = false)
    private Integer id;

    @Column(name = "Ngay_DayDU")
    private Instant ngayDaydu;

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

}