package com.example.quanlychitieuv2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class SoHuId implements Serializable {
    private static final long serialVersionUID = -3825352403975678533L;

    @NotNull
    @Column(name = "NguoiDuocCapQuyen_Id", nullable = false)
    private Integer userDuocCapId;

    @NotNull
    @Column(name = "VT_Id", nullable = false)
    private Integer vtId;

    @NotNull
    @Column(name = "Role_Id", nullable = false)
    private String roleId;

}