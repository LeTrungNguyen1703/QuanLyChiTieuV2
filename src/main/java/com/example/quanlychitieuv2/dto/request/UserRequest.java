package com.example.quanlychitieuv2.dto.request;

import com.example.quanlychitieuv2.entity.User;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link User}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {
    @NotNull
    @Size(max = 255)
    private String ndTen;
    @NotNull
    @Size(max = 255)
    @Email(message = "Email khong hop le", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$ ")
    private String ndEmail;
    @NotNull
    @Size(max = 255)
    private String ndMatkhau;

    private List<String> danhSachQuyen;
}