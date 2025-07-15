package com.example.quanlychitieuv2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.PhuongThucThanhToan}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhuongThucThanhToanRequest implements Serializable {
    @NotNull
    @Size(max = 100)
    @NotEmpty
    @NotBlank
    private String ptttLoai;
    private String ptttSoTk;
}