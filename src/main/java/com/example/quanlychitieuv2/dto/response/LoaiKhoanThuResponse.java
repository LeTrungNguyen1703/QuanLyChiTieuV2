package com.example.quanlychitieuv2.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.LoaiKhoanThu}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoaiKhoanThuResponse implements Serializable {
    private String lktTen;
}