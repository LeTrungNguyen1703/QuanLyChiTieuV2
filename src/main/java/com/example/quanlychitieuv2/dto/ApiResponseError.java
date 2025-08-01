package com.example.quanlychitieuv2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@SuperBuilder
public class ApiResponseError<Res> extends ApiResponseSuccess<Res> {

    String path;            // Đường dẫn URL nơi lỗi xảy ra
    Date timestamp;

    public ApiResponseError(String message, Integer status, String path, Date timestamp) {
        super(message, status);
        this.path = path;
        this.timestamp = timestamp;
    }

    public ApiResponseError(String message, Integer status) {
        super(message, status);
    }
}
