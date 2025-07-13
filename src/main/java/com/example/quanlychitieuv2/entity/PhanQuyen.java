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
@Table(name = "phan_quyen")
public class PhanQuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PQ_Id", nullable = false)
    private Integer id;

    @Size(max = 256)
    @NotNull
    @Column(name = "PQ_Ten", nullable = false, length = 256)
    private String pqTen;

    @OneToMany(mappedBy = "pq", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<NguoiDung> nguoiDungs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pq", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<QuyenTruyCap> quyenTruyCaps = new LinkedHashSet<>();

}