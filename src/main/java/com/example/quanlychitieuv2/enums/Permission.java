package com.example.quanlychitieuv2.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

/**
 * Enum định nghĩa các quyền có thể có đối với ví tiền trong hệ thống
 * Được sử dụng để quản lý phân quyền người dùng đối với các ví tiền
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
public enum Permission {

    VIEW_WALLET("VIEW_WALLET"),                     // Quyền xem ví tiền
    EDIT_WALLET("EDIT_WALLET"),                     // Quyền chỉnh sửa thông tin ví tiền
    DELETE_WALLET("DELETE_WALLET"),                 // Quyền xóa ví tiền
    ADD_TRANSACTION("ADD_TRANSACTION"),             // Quyền thêm giao dịch
    EDIT_TRANSACTION("EDIT_TRANSACTION"),           // Quyền chỉnh sửa giao dịch
    DELETE_TRANSACTION("DELETE_TRANSACTION"),       // Quyền xóa giao dịch
    GRANT_PERMISSION("GRANT_PERMISSION"),           // Quyền cấp quyền cho người khác
    ADD_USER_INTO_WALLET("ADD_USER_INTO_WALLET"),   // Quyền thêm User khác vào ví của Owner
    ;
    String value;

    /**
     * Lấy tất cả các giá trị quyền dưới dạng Set<String>
     *
     * @return Set<String> chứa tất cả các giá trị quyền
     */
    public static Set<String> getAllPermissions() {
        Set<String> permissions = new HashSet<>();
        for (Permission permission : values()) {
            permissions.add(permission.getValue());
        }
        return permissions;
    }
}
