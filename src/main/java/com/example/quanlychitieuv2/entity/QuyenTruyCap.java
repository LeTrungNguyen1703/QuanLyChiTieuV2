package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quyen_truy_cap")
public class QuyenTruyCap {
    @Id
    @Column(name = "CN_Id", nullable = false)
    private Integer id;

    @MapsId
    @ManyToOne(cascade =  {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    },fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CN_Id", nullable = false)
    private ChucNang chucNang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PQ_Id")
    private PhanQuyen pq;

    @NotNull
    @Column(name = "QTC_ChoPhep", nullable = false)
    private Boolean qtcChophep = false;

}