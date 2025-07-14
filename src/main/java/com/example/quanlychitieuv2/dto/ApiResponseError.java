package com.example.quanlychitieuv2.dto;

public class ApiResponseError<Res> extends ApiResponseSuccess<Res> {

    public ApiResponseError(String message, Integer status, Res data) {
        super(message, status, data);
    }
}
