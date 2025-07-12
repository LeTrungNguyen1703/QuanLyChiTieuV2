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
@Table(name = "phuong_thuc_thanh_toan")
public class PhuongThucThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PTTT_Id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "PTTT_LOAI", nullable = false, length = 100)
    private String ptttLoai;

    @Lob
    @Column(name = "PTTT_SO_TK")
    private String ptttSoTk;

    @OneToMany(mappedBy = "pttt", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanChi> khoanChis = new LinkedHashSet<>();

}