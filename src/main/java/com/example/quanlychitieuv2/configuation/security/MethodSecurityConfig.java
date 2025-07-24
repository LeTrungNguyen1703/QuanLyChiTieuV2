package com.example.quanlychitieuv2.configuation.security;

import com.example.quanlychitieuv2.service.WalletPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * Cấu hình bảo mật phương thức để sử dụng biểu thức bảo mật tùy chỉnh
 * cho việc phân quyền theo ví tiền
 */
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class MethodSecurityConfig {

    private final WalletPermissionService walletPermissionService;

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        return new WalletMethodSecurityExpressionHandler(walletPermissionService);
    }
}
