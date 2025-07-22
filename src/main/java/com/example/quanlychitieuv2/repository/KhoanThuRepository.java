package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.KhoanThu;
import com.example.quanlychitieuv2.entity.ViTien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface KhoanThuRepository extends JpaRepository<KhoanThu, Integer> {

    @Query("""
                select kt
                from KhoanThu kt
                join kt.vt vt
                where vt.id = :vtId
                and kt.ngay.ngayDaydu between :start AND :end
            """)
    List<KhoanThu> findByVtAndThang(
            @Param("vtId") Integer viTienId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

}