package com.example.quanlychitieuv2.service;

import com.example.quanlychitieuv2.entity.SoHu;
import com.example.quanlychitieuv2.entity.User;
import com.example.quanlychitieuv2.entity.ViTien;

import java.util.Set;

/**
 * Interface để kiểm tra quyền hạn của người dùng đối với ví tiền
 * Dựa trên bảng SoHu để xác định người dùng có quyền gì đối với ví tiền nào
 */
public interface WalletPermissionService {

    /**
     * Kiểm tra xem người dùng có quyền truy cập vào ví tiền hay không
     * @param userId ID của người dùng
     * @param walletId ID của ví tiền
     * @return true nếu có quyền, false nếu không có quyền
     */
    boolean hasAccess(Integer userId, Integer walletId);

    /**
     * Kiểm tra xem người dùng có quyền cụ thể đối với ví tiền hay không
     * @param userId ID của người dùng
     * @param walletId ID của ví tiền
     * @param permission Quyền cần kiểm tra
     * @return true nếu có quyền cụ thể, false nếu không có quyền
     */
    boolean hasPermission(Integer userId, Integer walletId, String permission);

    /**
     * Lấy tất cả các quyền của người dùng đối với ví tiền
     * @param userId ID của người dùng
     * @param walletId ID của ví tiền
     * @return Tập hợp các quyền
     */
    Set<String> getPermissions(Integer userId, Integer walletId);

    /**
     * Kiểm tra xem người dùng có phải là chủ của ví tiền hay không
     * @param userId ID của người dùng
     * @param walletId ID của ví tiền
     * @return true nếu là chủ, false nếu không phải
     */
    boolean isOwner(Integer userId, Integer walletId);
}
