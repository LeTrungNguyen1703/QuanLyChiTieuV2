package com.example.quanlychitieuv2.dto.request.LoaiVi;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoaiViRequest {

    @Size(max = 256)
    @NotNull
    private String lvTen;
}
