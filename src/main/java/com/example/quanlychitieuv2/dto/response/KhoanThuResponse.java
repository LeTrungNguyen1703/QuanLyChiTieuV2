package com.example.quanlychitieuv2.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.KhoanThu}
 */
public record KhoanThuResponse(Integer id, Integer ndId, Integer ngayId, LocalDate ngayNgayDaydu, Integer vtId,
                               String vtTenVi, Double vtVtSodu, Integer lktId, String lktLktTen,
                               @NotNull @Positive BigDecimal ktSotien) implements Serializable {
}