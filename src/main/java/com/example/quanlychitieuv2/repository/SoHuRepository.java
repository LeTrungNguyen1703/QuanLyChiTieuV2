package com.example.quanlychitieuv2.repository;

import com.example.quanlychitieuv2.entity.SoHu;
import com.example.quanlychitieuv2.entity.SoHuId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SoHuRepository extends JpaRepository<SoHu, SoHuId> {
    /**
     * Tìm tất cả các bản ghi SoHu của một người dùng
     * @param userId ID của người dùng được cấp quyền
     * @return Danh sách các bản ghi SoHu
     */
    List<SoHu> findByIdUserDuocCapId(Integer userId);

    /**
     * Tìm bản ghi SoHu dựa trên người dùng và ví tiền
     * @param userId ID của người dùng được cấp quyền
     * @param walletId ID của ví tiền
     * @return Optional chứa bản ghi SoHu nếu tồn tại
     */
    Optional<SoHu> findByIdUserDuocCapIdAndIdVtId(Integer userId, Integer walletId);

    /**
     * Kiểm tra xem bản ghi SoHu có tồn tại không dựa trên người dùng và ví tiền
     * @param userId ID của người dùng được cấp quyền
     * @param walletId ID của ví tiền
     * @return true nếu tồn tại, false nếu không
     */
    boolean existsByIdUserDuocCapIdAndIdVtId(Integer userId, Integer walletId);

    /**
     * Tìm tất cả các bản ghi SoHu của một ví tiền
     * @param walletId ID của ví tiền
     * @return Danh sách các bản ghi SoHu
     */
    List<SoHu> findByIdVtId(Integer walletId);

    /**
     * Tìm tất cả các bản ghi SoHu mà người dùng là chủ (userCapQuyenId == null)
     * @param userId ID của người dùng được cấp quyền
     * @return Danh sách các bản ghi SoHu
     */
    List<SoHu> findByIdUserDuocCapIdAndUserCapQuyenIdIsNull(Integer userId);
}