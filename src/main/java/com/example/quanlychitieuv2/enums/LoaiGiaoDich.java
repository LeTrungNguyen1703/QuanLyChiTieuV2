package com.example.quanlychitieuv2.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum LoaiGiaoDich {
    THU("Thu"),
    CHI("CHI");

    String value;
}
