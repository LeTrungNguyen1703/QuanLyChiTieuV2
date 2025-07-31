package com.example.quanlychitieuv2.configuation.security;

import com.example.quanlychitieuv2.enums.Permission;
import com.example.quanlychitieuv2.service.WalletPermissionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Lớp biểu thức bảo mật tùy chỉnh cho phép kiểm tra quyền truy cập vào ví tiền
 * Có thể sử dụng trong annotations @PreAuthorize và @PostAuthorize
 */
@Getter
@Setter
public class WalletSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;


    private final WalletPermissionService walletPermissionService;

    public WalletSecurityExpressionRoot(Authentication authentication, WalletPermissionService walletPermissionService) {
        super(authentication);
        this.walletPermissionService = walletPermissionService;
    }

    /**
     * Kiểm tra xem người dùng hiện tại có quyền truy cập vào ví tiền hay không
     * @param walletId ID của ví tiền
     * @return true nếu có quyền, false nếu không có quyền
     */
    public boolean hasWalletAccess(Integer walletId) {
        String currentUsername = getCurrentUsername();
        return walletPermissionService.hasAccess(currentUsername, walletId);
    }

    /**
     * Kiểm tra xem người dùng hiện tại có quyền cụ thể đối với ví tiền hay không
     * @param walletId ID của ví tiền
     * @param permission Quyền cần kiểm tra
     * @return true nếu có quyền cụ thể, false nếu không có quyền
     */
    public boolean hasWalletPermission(Integer walletId, String permission) {
        String currentUsername = getCurrentUsername();
        return walletPermissionService.hasPermission(currentUsername, walletId, permission);
    }

    /**
     * Kiểm tra xem người dùng hiện tại có phải là chủ của ví tiền hay không
     * @param walletId ID của ví tiền
     * @return true nếu là chủ, false nếu không phải
     */
    public boolean isWalletOwner(Integer walletId) {
        String currentUsername = getCurrentUsername();
        return walletPermissionService.isOwner(currentUsername, walletId);
    }

    /**
     * Lấy tên đăng nhập của người dùng hiện tại từ thông tin xác thực
     * @return Tên đăng nhập của người dùng hiện tại
     */
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
