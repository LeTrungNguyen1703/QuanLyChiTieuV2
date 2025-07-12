package com.example.quanlychitieuv2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class SoHuId implements Serializable {
    private static final long serialVersionUID = -3825352403975678533L;
    @NotNull
    @Column(name = "ND_Id", nullable = false)
    private Integer ndId;

    @NotNull
    @Column(name = "VT_Id", nullable = false)
    private Integer vtId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SoHuId entity = (SoHuId) o;
        return Objects.equals(this.ndId, entity.ndId) &&
                Objects.equals(this.vtId, entity.vtId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ndId, vtId);
    }

}