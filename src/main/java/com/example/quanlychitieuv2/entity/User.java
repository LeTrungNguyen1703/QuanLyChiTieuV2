package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<SoHu> soHus = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> danhSachQuyen;

    @PrePersist
    public void prePersist() {
        if (ndNgaytao == null) {
            ndNgaytao = LocalDate.now();
        }
    }
}