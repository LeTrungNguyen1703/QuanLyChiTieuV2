package com.example.quanlychitieuv2.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponseSuccess<Res>{
    String message = "Success";
    Integer status = 200;
    Res data;

    public ApiResponseSuccess(String message, Integer status, Res data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ApiResponseSuccess(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public ApiResponseSuccess(Res data) {
        this.data = data;
    }
}
