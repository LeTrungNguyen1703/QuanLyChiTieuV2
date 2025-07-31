package com.example.quanlychitieuv2.configuation.security;

public interface IWalletSecurity {

    /**
     * Kiểm tra xem người dùng hiện tại có quyền truy cập vào ví tiền hay không.
     *
     * @param walletId ID của ví tiền
     * @return true nếu có quyền, false nếu không có
     */
    boolean hasWalletAccess(Integer walletId);

    /**
     * Kiểm tra xem người dùng hiện tại có quyền cụ thể đối với ví tiền hay không.
     *
     * @param walletId   ID của ví tiền
     * @param permission Quyền cần kiểm tra (ví dụ: "ADD_TRANSACTION", "VIEW", ...)
     * @return true nếu có quyền cụ thể, false nếu không
     */
    boolean hasWalletPermission(Integer walletId, String permission);

    /**
     * Kiểm tra xem người dùng hiện tại có phải là chủ của ví tiền hay không.
     *
     * @param walletId ID của ví tiền
     * @return true nếu là chủ, false nếu không phải
     */
    boolean isWalletOwner(Integer walletId);

}
