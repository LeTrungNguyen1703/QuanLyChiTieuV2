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
@Table(name = "loai_khoan_chi")
public class LoaiKhoanChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LKC_Id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "LKC_Ten", nullable = false)
    private String lkcTen;

    @Lob
    @Column(name = "LKC_MoTa")
    private String lkcMota;

    @OneToMany(mappedBy = "lkc", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<HanMucChi> hanMucChis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "lkc", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanChi> khoanChis = new LinkedHashSet<>();

}