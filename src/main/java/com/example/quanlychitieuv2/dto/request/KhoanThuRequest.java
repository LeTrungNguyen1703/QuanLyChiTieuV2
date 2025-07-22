package com.example.quanlychitieuv2.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.KhoanThu}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KhoanThuRequest implements Serializable {
    private Integer userId;
    private Integer vtId;
    private Integer lktId;
    private String moTa;
    private String tenKhoanThu;
    @NotNull
    @Positive
    private BigDecimal ktSotien;
}