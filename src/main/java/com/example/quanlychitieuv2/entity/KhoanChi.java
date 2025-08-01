package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "khoan_chi")
public class KhoanChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KC_Id", nullable = false)
    private Integer id;

    @Column(name = "KC_Ten", length = 256)
    private String kcTen;

    @NotNull
    @Column(name = "KC_SoTien", nullable = false, precision = 10, scale = 2)
    private BigDecimal kcSotien;

    @Lob
    @Column(name = "KC_MoTa")
    private String kcMota;

    @NotNull
    @Column(name = "KC_LapLai", nullable = false)
    private Boolean kcLaplai = false;

    @Column(name = "KC_NgayKetThucLapLai")
    private LocalDate kcNgayketthuclaplai;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LKC_Id", nullable = false)
    private LoaiKhoanChi lkc;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VT_Id", nullable = false)
    @NotNull
    private ViTien vt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Ngay_Id", nullable = false)
    private Ngay ngay;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PTTT_Id", nullable = false)
    private PhuongThucThanhToan pttt;

}