package com.example.quanlychitieuv2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.Ngay}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NgayDto implements Serializable {
    private LocalDate ngayDaydu;
}