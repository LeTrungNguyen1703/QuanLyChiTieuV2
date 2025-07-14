package com.example.quanlychitieuv2.dto.request;

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
public class TrangThaiRequest {

    @Size(max = 256)
    @NotNull
    private String ttTen;

    @NotNull
    private String ttMota;
}
