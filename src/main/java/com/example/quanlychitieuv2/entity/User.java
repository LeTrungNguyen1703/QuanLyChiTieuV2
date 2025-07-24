package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_Ten", nullable = false)
    private String ndTen;

    @Size(max = 255)
    @Column(name = "user_Email")
    private String ndEmail;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_MatKhau", nullable = false)
    private String ndMatkhau;

    @Column(name = "user_NgayTao")
    private LocalDate ndNgaytao;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanChi> khoanChis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<KhoanThu> khoanThus = new LinkedHashSet<>();

    // Những ví mà người dùng được cấp quyền (người dùng này là userDuocCapQuyenId trong SoHu)
    @OneToMany(mappedBy = "userDuocCapQuyenId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<SoHu> soHusDuocCap = new LinkedHashSet<>();
    
    // Những ví mà người dùng đã cấp quyền cho người khác (người dùng này là userCapQuyenId trong SoHu)
    @OneToMany(mappedBy = "userCapQuyenId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<SoHu> soHusDaCap = new LinkedHashSet<>();

    @PrePersist
    public void prePersist() {
        if (ndNgaytao == null) {
            ndNgaytao = LocalDate.now();
        }
    }
}