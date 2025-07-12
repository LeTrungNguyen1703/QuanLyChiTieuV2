package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ND_Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PQ_Id")
    private PhanQuyen pq;

    @Size(max = 255)
    @NotNull
    @Column(name = "ND_Ten", nullable = false)
    private String ndTen;

    @Size(max = 255)
    @Column(name = "ND_Email")
    private String ndEmail;

    @Size(max = 255)
    @NotNull
    @Column(name = "ND_MatKhau", nullable = false)
    private String ndMatkhau;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ND_NgayTao")
    private Instant ndNgaytao;

    @Size(max = 50)
    @NotNull
    @Column(name = "ND_TrangThai", nullable = false, length = 50)
    private String ndTrangthai;

    @Size(max = 10)
    @Column(name = "ND_Avatar", length = 10)
    private String ndAvatar;

    @OneToMany(mappedBy = "nd", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanChi> khoanChis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "nd", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanThu> khoanThus = new LinkedHashSet<>();

    @OneToMany(mappedBy = "nd", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<SoHu> soHus = new LinkedHashSet<>();
}