package com.example.quanlychitieuv2.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.QuyenTruyCap}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuyenTruyCapDto implements Serializable {
    private Integer chucNangId;
    private Integer pqId;
    @NotNull
    private Boolean qtcChophep = false;
}