package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.entity.Role;
import com.example.quanlychitieuv2.entity.SoHu;
import com.example.quanlychitieuv2.entity.User;
import com.example.quanlychitieuv2.enums.Permission;
import com.example.quanlychitieuv2.repository.SoHuRepository;
import com.example.quanlychitieuv2.repository.UserRepository;
import com.example.quanlychitieuv2.service.WalletPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Triển khai của WalletPermissionService để kiểm tra quyền hạn của người dùng đối với ví tiền
 * Dựa trên bảng SoHu để xác định người dùng có quyền gì đối với ví tiền nào
 */
@Service
@RequiredArgsConstructor
public class WalletPermissionServiceImpl implements WalletPermissionService {

    private final SoHuRepository soHuRepository;
    private final UserRepository userRepository;

    /**
     * Kiểm tra xem người dùng có quyền truy cập vào ví tiền hay không
     *
     * @param username Tên người dùng
     * @param walletId ID của ví tiền
     * @return true nếu người dùng có quyền truy cập, ngược lại trả về false
     */
    @Override
    public boolean hasAccess(String username, Integer walletId) {
        // Tìm user dựa trên username
        User user = getUserByUsername(username);
        if (user == null) {
            return false;
        }

        // Người dùng có quyền truy cập ví tiền nếu có bất kỳ bản ghi SoHu nào liên kết họ với ví tiền đó
        return soHuRepository.existsByIdUserDuocCapIdAndIdVtId(user.getId(), walletId);
    }

    /**
     * Kiểm tra xem người dùng có quyền cụ thể đối với ví tiền hay không
     *
     * @param username Tên người dùng
     * @param walletId ID của ví tiền
     * @param permission Quyền cần kiểm tra
     * @return true nếu người dùng có quyền, ngược lại trả về false
     */
    @Override
    public boolean hasPermission(String username, Integer walletId, String permission) {
        // Nếu người dùng là chủ ví, họ có tất cả các quyền
        if (isOwner(username, walletId)) {
            return true;
        }

        // Kiểm tra xem người dùng có quyền cụ thể đối với ví tiền hay không
        Set<String> permissions = getPermissions(username, walletId);
        return permissions.contains(permission);
    }

    /**
     * Lấy tất cả các quyền của người dùng đối với ví tiền
     *
     * @param username Tên người dùng
     * @param walletId ID của ví tiền
     * @return Tập hợp các quyền của người dùng đối với ví tiền
     */
    @Override
    public Set<String> getPermissions(String username, Integer walletId) {
        // Tìm user dựa trên username
        User user = getUserByUsername(username);
        if (user == null) {
            return new HashSet<>();
        }

        // Lấy bản ghi SoHu liên kết người dùng với ví tiền
        Optional<SoHu> soHuOptional = soHuRepository.findByIdUserDuocCapIdAndIdVtId(user.getId(), walletId);

        if (soHuOptional.isEmpty()) {
            return new HashSet<>();
        }

        SoHu soHu = soHuOptional.get();
        Role role = soHu.getRole();

        // Nếu người dùng là chủ ví, trả về tất cả các quyền có thể
        if (isOwner(username, walletId)) {
            return getAllPossiblePermissions();
        }

        // Lấy tất cả các quyền từ role được gán cho người dùng đối với ví tiền này
        return role.getPermissions();
    }

    /**
     * Kiểm tra xem người dùng có phải là chủ ví hay không
     *
     * @param username Tên người dùng
     * @param walletId ID của ví tiền
     * @return true nếu người dùng là chủ ví, ngược lại trả về false
     */
    @Override
    public boolean isOwner(String username, Integer walletId) {
        // Tìm user dựa trên username
        User user = getUserByUsername(username);
        if (user == null) {
            return false;
        }

        // Người dùng là chủ ví nếu họ là người được cấp quyền và không có người cấp quyền (userCapQuyenId == null)
        Optional<SoHu> soHuOptional = soHuRepository.findByIdUserDuocCapIdAndIdVtId(user.getId(), walletId);
        return soHuOptional.isPresent() && soHuOptional.get().getUserCapQuyenId() == null;
    }

    /**
     * Lấy tất cả các quyền có thể có trong hệ thống
     * @return Tập hợp tất cả các quyền
     */
    private Set<String> getAllPossiblePermissions() {
        // Sử dụng phương thức static từ enum OwnerPermisson
        return Permission.getAllPermissions();
    }

    /**
     * Lấy thông tin người dùng từ tên đăng nhập
     * @param username Tên đăng nhập của người dùng
     * @return Đối tượng User nếu tìm thấy, null nếu không tìm thấy
     */
    private User getUserByUsername(String username) {
        return userRepository.findByNdTen(username).orElse(null);
    }
}
