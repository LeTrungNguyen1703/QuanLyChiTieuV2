package com.example.quanlychitieuv2.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "so_hu")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SoHu {
    @EmbeddedId
    private SoHuId id;

    //User được cấp quyền của ví: như chỉ được xem
    @MapsId("userDuocCapId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_duoc_cap_quyen_id", nullable = false)
    private User userDuocCapQuyenId;

    //User câp quyền, nếu nó null thì được xem như là chủ của ví đó, có toan quyền
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_cap_quyen_id")
    private User userCapQuyenId;

    @MapsId("vtId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "VT_Id", nullable = false)
    private ViTien vt;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "Role_Id", nullable = false)
    private Role role;
}