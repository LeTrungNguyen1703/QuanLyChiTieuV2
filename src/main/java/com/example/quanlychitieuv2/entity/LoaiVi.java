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
@Table(name = "loai_vi")
public class LoaiVi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LV_Id", nullable = false)
    private Integer id;

    @Size(max = 256)
    @NotNull
    @Column(name = "LV_Ten", nullable = false, length = 256)
    private String lvTen;

    @OneToMany(mappedBy = "lv", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<ViTien> viTiens = new LinkedHashSet<>();

}