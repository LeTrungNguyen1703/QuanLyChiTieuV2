package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "khoan_thu")
public class KhoanThu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KT_Id", nullable = false)
    private Integer id;
    @Column(name = "KT_MoTa")
    private String moTa;
    @Column(name = "KT_TenKhoanThu", nullable = false)
    private String tenKhoanThu;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Ngay_Id", nullable = false)
    private Ngay ngay;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VT_Id", nullable = false)
    private ViTien vt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LKT_Id", nullable = false)
    private LoaiKhoanThu lkt;

    @NotNull
    @Column(name = "KT_SoTien", nullable = false, precision = 10, scale = 2)
    private BigDecimal ktSotien;
}