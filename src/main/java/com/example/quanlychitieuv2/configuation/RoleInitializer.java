package com.example.quanlychitieuv2.configuation;

import com.example.quanlychitieuv2.entity.Role;
import com.example.quanlychitieuv2.enums.Permission;
import com.example.quanlychitieuv2.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Lớp này chịu trách nhiệm khởi tạo các vai trò mặc định trong hệ thống khi ứng dụng được khởi chạy
 * Sử dụng các quyền từ enum OwnerPermisson để cấu hình quyền cho từng vai trò
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RoleInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;

    /**
     * Phương thức này được gọi khi ứng dụng khởi chạy để khởi tạo các vai trò
     */
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.info("Khởi tạo các vai trò mặc định cho hệ thống...");

        // Tạo vai trò OWNER với tất cả các quyền
        initOwnerRole();

        // Tạo vai trò VIEWER chỉ có quyền xem
        initViewerRole();

        // Tạo vai trò CONTRIBUTOR có quyền xem và thêm giao dịch
        initContributorRole();

        // Tạo vai trò MANAGER có thể xem, thêm, sửa, xóa giao dịch và chỉnh sửa ví
        initManagerRole();

        log.info("Khởi tạo các vai trò mặc định hoàn tất");
    }

    /**
     * Khởi tạo vai trò OWNER với tất cả quyền
     */
    private void initOwnerRole() {
        if (!roleRepository.existsById("OWNER")) {
            Role ownerRole = Role.builder()
                .name("OWNER")
                .description("Chủ sở hữu ví tiền với đầy đủ quyền")
                .permissions(Permission.getAllPermissions())
                .build();

            roleRepository.save(ownerRole);
            log.info("Đã tạo vai trò OWNER với {} quyền", ownerRole.getPermissions().size());
        } else {
            log.info("Vai trò OWNER đã tồn tại");
        }
    }

    /**
     * Khởi tạo vai trò VIEWER chỉ có quyền xem
     */
    private void initViewerRole() {
        if (!roleRepository.existsById("VIEWER")) {
            Set<String> viewerPermissions = new HashSet<>();
            viewerPermissions.add(Permission.VIEW_WALLET.getValue());

            Role viewerRole = Role.builder()
                .name("VIEWER")
                .description("Người xem, chỉ có quyền xem ví tiền")
                .permissions(viewerPermissions)
                .build();

            roleRepository.save(viewerRole);
            log.info("Đã tạo vai trò VIEWER với {} quyền", viewerRole.getPermissions().size());
        } else {
            log.info("Vai trò VIEWER đã tồn tại");
        }
    }

    /**
     * Khởi tạo vai trò CONTRIBUTOR có quyền xem và thêm giao dịch
     */
    private void initContributorRole() {
        if (!roleRepository.existsById("CONTRIBUTOR")) {
            Set<String> contributorPermissions = new HashSet<>();
            contributorPermissions.add(Permission.VIEW_WALLET.getValue());
            contributorPermissions.add(Permission.ADD_TRANSACTION.getValue());

            Role contributorRole = Role.builder()
                .name("CONTRIBUTOR")
                .description("Người đóng góp, có thể xem và thêm giao dịch")
                .permissions(contributorPermissions)
                .build();

            roleRepository.save(contributorRole);
            log.info("Đã tạo vai trò CONTRIBUTOR với {} quyền", contributorRole.getPermissions().size());
        } else {
            log.info("Vai trò CONTRIBUTOR đã tồn tại");
        }
    }

    /**
     * Khởi tạo vai trò MANAGER có quyền xem, thêm, sửa, xóa giao dịch và chỉnh sửa ví
     */
    private void initManagerRole() {
        if (!roleRepository.existsById("MANAGER")) {
            Set<String> managerPermissions = new HashSet<>();
            managerPermissions.add(Permission.VIEW_WALLET.getValue());
            managerPermissions.add(Permission.EDIT_WALLET.getValue());
            managerPermissions.add(Permission.ADD_TRANSACTION.getValue());
            managerPermissions.add(Permission.EDIT_TRANSACTION.getValue());
            managerPermissions.add(Permission.DELETE_TRANSACTION.getValue());

            Role managerRole = Role.builder()
                .name("MANAGER")
                .description("Quản lý, có thể quản lý ví tiền và các giao dịch")
                .permissions(managerPermissions)
                .build();

            roleRepository.save(managerRole);
            log.info("Đã tạo vai trò MANAGER với {} quyền", managerRole.getPermissions().size());
        } else {
            log.info("Vai trò MANAGER đã tồn tại");
        }
    }
}
