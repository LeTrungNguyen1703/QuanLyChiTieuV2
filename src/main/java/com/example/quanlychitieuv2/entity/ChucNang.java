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
@Table(name = "chuc_nang")
public class ChucNang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CN_Id", nullable = false)
    private Integer id;

    @Size(max = 256)
    @NotNull
    @Column(name = "CN_TEN", nullable = false, length = 256)
    private String cnTen;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, fetch = FetchType.LAZY)
    private Set<QuyenTruyCap> quyenTruyCaps = new LinkedHashSet<QuyenTruyCap>();

}