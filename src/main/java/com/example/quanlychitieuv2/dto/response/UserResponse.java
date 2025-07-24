package com.example.quanlychitieuv2.dto.response;

import com.example.quanlychitieuv2.dto.response.Authentication.RoleResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link com.example.quanlychitieuv2.entity.User}
 */
@AllArgsConstructor
@Getter
public class UserResponse implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 255)
    private final String ndTen;
    @Size(max = 255)
    private final String ndEmail;
    private final LocalDate ndNgaytao;
}