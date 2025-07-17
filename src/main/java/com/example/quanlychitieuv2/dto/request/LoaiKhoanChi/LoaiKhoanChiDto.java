package com.example.quanlychitieuv2.dto.request.LoaiKhoanChi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.LoaiKhoanChi}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoaiKhoanChiDto implements Serializable {
    @NotNull
    @Size(max = 255)
    @NotEmpty
    @NotBlank
    private String lkcTen;
    private String lkcMota;
}