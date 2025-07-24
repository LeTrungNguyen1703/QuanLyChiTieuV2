package com.example.quanlychitieuv2.configuation.security;

import com.example.quanlychitieuv2.service.WalletPermissionService;
import lombok.Setter;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Lớp biểu thức bảo mật tùy chỉnh cho phép kiểm tra quyền truy cập vào ví tiền
 * Có thể sử dụng trong annotations @PreAuthorize và @PostAuthorize
 */
public class WalletSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    @Setter
    private Object filterObject;
    @Setter
    private Object returnObject;

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
        Integer currentUserId = getCurrentUserId();
        return walletPermissionService.hasAccess(currentUserId, walletId);
    }

    /**
     * Kiểm tra xem người dùng hiện tại có quyền cụ thể đối với ví tiền hay không
     * @param walletId ID của ví tiền
     * @param permission Quyền cần kiểm tra
     * @return true nếu có quyền cụ thể, false nếu không có quyền
     */
    public boolean hasWalletPermission(Integer walletId, String permission) {
        Integer currentUserId = getCurrentUserId();
        return walletPermissionService.hasPermission(currentUserId, walletId, permission);
    }

    /**
     * Kiểm tra xem người dùng hiện tại có phải là chủ của ví tiền hay không
     * @param walletId ID của ví tiền
     * @return true nếu là chủ, false nếu không phải
     */
    public boolean isWalletOwner(Integer walletId) {
        Integer currentUserId = getCurrentUserId();
        return walletPermissionService.isOwner(currentUserId, walletId);
    }

    /**
     * Lấy ID của người dùng hiện tại từ thông tin xác thực
     * @return ID của người dùng hiện tại
     */
    private Integer getCurrentUserId() {
        // Đây là cách đơn giản để lấy ID người dùng từ đối tượng xác thực
        // Bạn có thể cần điều chỉnh tùy theo cách bạn lưu trữ thông tin người dùng trong đối tượng xác thực
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Integer) {
            return (Integer) authentication.getPrincipal();
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
