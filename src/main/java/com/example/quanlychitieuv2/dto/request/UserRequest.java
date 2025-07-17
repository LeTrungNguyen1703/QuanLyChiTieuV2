package com.example.quanlychitieuv2.dto.request;

import com.example.quanlychitieuv2.entity.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {
    private Integer pqId;
    @NotNull
    @Size(max = 255)
    @NotEmpty
    @NotBlank
    private String ndTen;
    @NotNull
    @Size(max = 255)
    @Email(message = "Email khong hop le", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$ ")
    @NotEmpty
    @NotBlank
    private String ndEmail;
    @NotNull
    @Size(max = 255)
    @NotEmpty
    @NotBlank
    private String ndMatkhau;
}