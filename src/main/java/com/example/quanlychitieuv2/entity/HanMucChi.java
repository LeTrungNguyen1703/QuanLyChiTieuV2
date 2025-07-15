package com.example.quanlychitieuv2.entity;

import com.example.quanlychitieuv2.enums.TrangThaiHoatDong;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "han_muc_chi")
public class HanMucChi {
    @EmbeddedId
    private HanMucChiId id;

    @MapsId("lkcId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LKC_Id", nullable = false)
    private LoaiKhoanChi lkc;

    @MapsId("ngayId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Ngay_Id", nullable = false)
    private Ngay ngay;

    @MapsId("vtId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VT_Id", nullable = false)
    private ViTien vt;

    @Enumerated(EnumType.STRING)
    @Column(name = "tt_Ten", nullable = false)
    private TrangThaiHoatDong trangThaiHoatDong;

    @NotNull
    @Column(name = "HMC_SoTien", nullable = false, precision = 10, scale = 2)
    private BigDecimal hmcSotien;

    @Lob
    @Column(name = "HMC_MoTa")
    private String hmcMota;

}