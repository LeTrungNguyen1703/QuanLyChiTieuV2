package com.example.quanlychitieuv2.dto.request.LoaiKhoanThu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.LoaiKhoanThu}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoaiKhoanThuDto implements Serializable {
    @NotNull
    @Size(max = 256)
    @NotEmpty
    @NotBlank
    private String lktTen;
}