package com.example.quanlychitieuv2.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.LoaiKhoanChi}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class LoaiKhoanChiResponse implements Serializable {
    private Integer id;
    private String lkcTen;
    private String lkcMota;
}