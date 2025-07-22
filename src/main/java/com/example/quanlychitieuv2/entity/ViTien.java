package com.example.quanlychitieuv2.entity;

import com.example.quanlychitieuv2.enums.TrangThaiHoatDong;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vi_tien")
public class ViTien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VT_Id", nullable = false)
    private Integer id;

    @Column(name = "VT_Ten", nullable = false)
    private String tenVi;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LV_Id", nullable = false)
    private LoaiVi lv;

    @Enumerated(EnumType.STRING)
    @Column(name = "tt_Ten", nullable = false)
    private TrangThaiHoatDong trangThaiHoatDong;

    @NotNull
    @Column(name = "VT_SoDu", nullable = false)
    private Double vtSodu;

    @OneToMany(mappedBy = "vt", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<HanMucChi> hanMucChis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vt", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanChi> khoanChis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vt", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanThu> khoanThus = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vt", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<SoHu> soHus = new LinkedHashSet<>();

    public ViTien () {
        this.vtSodu = 0.0;
    }
}