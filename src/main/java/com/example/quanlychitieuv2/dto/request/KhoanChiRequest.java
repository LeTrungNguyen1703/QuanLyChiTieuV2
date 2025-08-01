package com.example.quanlychitieuv2.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.KhoanChi}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KhoanChiRequest implements Serializable {
    @NotNull
    private String kcTen;
    @NotNull
    private BigDecimal kcSotien;
    private String kcMota;
    private Boolean kcLaplai;
    private LocalDate kcNgayketthuclaplai;
    private Integer lkcId;
    private Integer vtId;
    private Integer ptttId;
}