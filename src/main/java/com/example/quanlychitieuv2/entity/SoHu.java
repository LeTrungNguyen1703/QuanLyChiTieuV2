package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "so_hu")
public class SoHu {
    @EmbeddedId
    private SoHuId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    @MapsId("vtId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "VT_Id", nullable = false)
    private ViTien vt;

}