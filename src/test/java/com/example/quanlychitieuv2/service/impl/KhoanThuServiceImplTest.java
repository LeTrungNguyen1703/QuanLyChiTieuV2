package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.KhoanThuRequest;
import com.example.quanlychitieuv2.dto.response.KhoanThuResponse;
import com.example.quanlychitieuv2.entity.*;
import com.example.quanlychitieuv2.mapper.impl.KhoanThuMapper;
import com.example.quanlychitieuv2.repository.KhoanThuRepository;
import com.example.quanlychitieuv2.repository.UserRepository;
import com.example.quanlychitieuv2.util.FindBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KhoanThuServiceImplTest {

    @Mock
    private KhoanThuRepository khoanThuRepository;

    @Mock
    private KhoanThuMapper khoanThuMapper;

    @Mock
    private FindBy findBy;
    
    @Mock
    private SecurityContext securityContext;
    
    @Mock
    private Authentication authentication;

    @InjectMocks
    private KhoanThuServiceImpl khoanThuService;

    private KhoanThuRequest khoanThuRequest;
    private KhoanThu khoanThu;
    private ViTien viTien;
    private User user;
    private LoaiKhoanThu loaiKhoanThu;
    private Ngay ngay;
    private KhoanThuResponse khoanThuResponse;

    @BeforeEach
    void setUp() {
        // Setup common test data
        khoanThuRequest = new KhoanThuRequest();
        khoanThuRequest.setVtId(1);
        khoanThuRequest.setLktId(2);
        khoanThuRequest.setMoTa("Test Mô tả");
        khoanThuRequest.setTenKhoanThu("Test Khoản Thu");
        khoanThuRequest.setKtSotien(new BigDecimal("100.00"));

        viTien = new ViTien();
        viTien.setId(1);
        viTien.setTenVi("Ví Test");
        viTien.setVtSodu(new BigDecimal("500.00"));

        user = new User();
        user.setId(1);
        user.setNdTen("testUser");

        loaiKhoanThu = new LoaiKhoanThu();
        loaiKhoanThu.setId(2);
        loaiKhoanThu.setLktTen("Loại khoản thu test");

        ngay = new Ngay();
        ngay.setId(1);
        ngay.setNgayDaydu(LocalDate.now());

        khoanThu = new KhoanThu();
        khoanThu.setId(1);
        khoanThu.setMoTa(khoanThuRequest.getMoTa());
        khoanThu.setTenKhoanThu(khoanThuRequest.getTenKhoanThu());
        khoanThu.setKtSotien(khoanThuRequest.getKtSotien());
        khoanThu.setVt(viTien);
        khoanThu.setUser(user);
        khoanThu.setLkt(loaiKhoanThu);
        khoanThu.setNgay(ngay);
        khoanThu.setAuditFields(new AuditFields());

        khoanThuResponse = KhoanThuResponse.builder()
                .id(1)
                .moTa(khoanThuRequest.getMoTa())
                .tenKhoanThu(khoanThuRequest.getTenKhoanThu())
                .ktSotien(khoanThuRequest.getKtSotien())
                .user(new KhoanThuResponse.UserDto(user.getId(), user.getNdTen()))
                .vt(new KhoanThuResponse.ViTienDto(viTien.getId(), viTien.getTenVi(), viTien.getVtSodu().doubleValue()))
                .lkt(new KhoanThuResponse.LoaiKhoanThuDto(loaiKhoanThu.getId(), loaiKhoanThu.getLktTen()))
                .ngay(new KhoanThuResponse.NgayDto(ngay.getId(), ngay.getNgayDaydu()))
                .auditFields(new AuditFields())
                .build();
    }

    @Test
    @DisplayName("Create KhoanThu - Success")
    void testCreateKhoanThuSuccess() {
        // Arrange
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");
        
        when(khoanThuMapper.toEntity(khoanThuRequest)).thenReturn(khoanThu);
        when(findBy.findViTienById(khoanThuRequest.getVtId())).thenReturn(viTien);
        when(findBy.findUserByName("testUser")).thenReturn(user);
        when(findBy.findLoaiKhoanThuById(khoanThuRequest.getLktId())).thenReturn(loaiKhoanThu);
        when(findBy.findNgayByNgayDayDu(any())).thenReturn(ngay);
        
        // ViTien will be updated with the new balance (500 + 100 = 600)
        BigDecimal expectedNewBalance = new BigDecimal("600.00");
        
        when(khoanThuRepository.save(any(KhoanThu.class))).thenAnswer(invocation -> {
            KhoanThu savedKhoanThu = invocation.getArgument(0);
            assertThat(savedKhoanThu.getVt().getVtSodu()).isEqualTo(expectedNewBalance);
            return savedKhoanThu;
        });
        
        when(khoanThuMapper.toRes(any(KhoanThu.class))).thenReturn(khoanThuResponse);

        // Act
        KhoanThuResponse result = khoanThuService.create(khoanThuRequest);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getTenKhoanThu()).isEqualTo("Test Khoản Thu");
        assertThat(result.getMoTa()).isEqualTo("Test Mô tả");
        assertThat(result.getKtSotien()).isEqualTo(new BigDecimal("100.00"));
        
        // Verify that the balance was updated correctly
        verify(khoanThuRepository).save(any(KhoanThu.class));
        
        // Verify all mock interactions
        verify(khoanThuMapper).toEntity(khoanThuRequest);
        verify(findBy).findViTienById(khoanThuRequest.getVtId());
        verify(findBy).findUserByName("testUser");
        verify(findBy).findLoaiKhoanThuById(khoanThuRequest.getLktId());
        verify(findBy).findNgayByNgayDayDu(any());
        verify(khoanThuMapper).toRes(any(KhoanThu.class));
    }

    @Test
    @DisplayName("Create KhoanThu - Updates ViTien Balance Correctly")
    void testCreateKhoanThuUpdatesBalance() {
        // Arrange
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");
        
        when(khoanThuMapper.toEntity(khoanThuRequest)).thenReturn(khoanThu);
        when(findBy.findViTienById(khoanThuRequest.getVtId())).thenReturn(viTien);
        when(findBy.findUserByName("testUser")).thenReturn(user);
        when(findBy.findLoaiKhoanThuById(khoanThuRequest.getLktId())).thenReturn(loaiKhoanThu);
        when(findBy.findNgayByNgayDayDu(any())).thenReturn(ngay);
        
        // Save will capture the KhoanThu argument to verify balance calculation
        when(khoanThuRepository.save(any(KhoanThu.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(khoanThuMapper.toRes(any(KhoanThu.class))).thenReturn(khoanThuResponse);

        // Act
        khoanThuService.create(khoanThuRequest);

        // Assert
        // Verify that the balance was updated correctly (500 + 100 = 600)
        verify(khoanThuRepository).save(argThat(kt -> 
            kt.getVt().getVtSodu().compareTo(new BigDecimal("600.00")) == 0));
    }

    @Test
    @DisplayName("Create KhoanThu - With No Authentication")
    void testCreateKhoanThuWithNoAuthentication() {
        // Arrange
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(null);
        
        when(khoanThuMapper.toEntity(khoanThuRequest)).thenReturn(khoanThu);
        when(findBy.findViTienById(khoanThuRequest.getVtId())).thenReturn(viTien);
        when(findBy.findLoaiKhoanThuById(khoanThuRequest.getLktId())).thenReturn(loaiKhoanThu);
        when(findBy.findNgayByNgayDayDu(any())).thenReturn(ngay);
        
        // In this case, user will be null
        khoanThu.setUser(null);
        
        when(khoanThuRepository.save(any(KhoanThu.class))).thenReturn(khoanThu);
        when(khoanThuMapper.toRes(any(KhoanThu.class))).thenReturn(khoanThuResponse);

        // Act
        KhoanThuResponse result = khoanThuService.create(khoanThuRequest);

        // Assert
        assertThat(result).isNotNull();
        verify(findBy, never()).findUserByName(anyString());
    }
}
