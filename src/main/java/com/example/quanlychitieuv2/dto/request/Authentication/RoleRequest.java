package com.example.quanlychitieuv2.dto.request.Authentication;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    String name;
    String description;
    Set<String> permissions;
}
