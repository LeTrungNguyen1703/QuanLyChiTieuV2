package com.example.quanlychitieuv2.dto.response;

import com.example.quanlychitieuv2.enums.TrangThaiHoatDong;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.ViTien}
 */
@Getter
@AllArgsConstructor
public class ViTienResponse implements Serializable {
    private Integer id;
    private String tenVi;
    private Double vtSodu;
    private LoaiViResponse loaiVi;
    private TrangThaiHoatDong trangThaiHoatDong;
}