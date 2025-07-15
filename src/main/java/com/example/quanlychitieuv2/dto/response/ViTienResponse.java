package com.example.quanlychitieuv2.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.ViTien}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViTienResponse implements Serializable {
    private Integer id;
    private String tenVi;
    private Integer lvId;
    private String lvLvTen;
    private Integer ttId;
    private String ttTtTen;
    private String ttTtMota;
    private Double vtSodu;
}