package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.KhoanChi;
import com.example.quanlychitieuv2.entity.ViTien;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface KhoanChiRepository extends JpaRepository<KhoanChi, Integer> {
    @Query("""
                select kc
                from KhoanChi kc
                join kc.vt vt
                where vt.id = :vtId
                and kc.ngay.ngayDaydu between :start AND :end
            """)
    List<KhoanChi> findByVtAndThang(
            @Param("vtId") Integer viTienId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
    
    List<KhoanChi> findByVtAndNgayNgayDaydu(@NotNull ViTien vt, LocalDate ngay_ngayDaydu);
}