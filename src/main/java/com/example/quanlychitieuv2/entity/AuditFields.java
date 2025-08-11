package com.example.quanlychitieuv2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditFields {

    @CreatedDate
    @Column(name = "create_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_at")
    LocalDateTime updatedAt;
}
