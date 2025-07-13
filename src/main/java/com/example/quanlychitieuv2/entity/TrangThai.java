package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "trang_thai")
public class TrangThai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TT_ID", nullable = false)
    private Integer id;

    @Size(max = 256)
    @NotNull
    @Column(name = "TT_TEN", nullable = false, length = 256)
    private String ttTen;

    @NotNull
    @Lob
    @Column(name = "TT_MOTA", nullable = false)
    private String ttMota;

    @OneToMany(mappedBy = "tt", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<HanMucChi> hanMucChis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tt", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<ViTien> viTiens = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tt", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<NguoiDung> nguoiDungs = new LinkedHashSet<>();
}