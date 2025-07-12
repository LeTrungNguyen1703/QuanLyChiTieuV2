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
public class HanMucChiId implements Serializable {
    private static final long serialVersionUID = -7064692250845758328L;
    @NotNull
    @Column(name = "LKC_Id", nullable = false)
    private Integer lkcId;

    @NotNull
    @Column(name = "Ngay_Id", nullable = false)
    private Integer ngayId;

    @NotNull
    @Column(name = "VT_Id", nullable = false)
    private Integer vtId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HanMucChiId entity = (HanMucChiId) o;
        return Objects.equals(this.ngayId, entity.ngayId) &&
                Objects.equals(this.lkcId, entity.lkcId) &&
                Objects.equals(this.vtId, entity.vtId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ngayId, lkcId, vtId);
    }

}