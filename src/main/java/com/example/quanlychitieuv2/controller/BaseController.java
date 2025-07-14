package com.example.quanlychitieuv2.controller;

import com.example.quanlychitieuv2.dto.ApiResponseSuccess;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<Req, Res, ID> {
    ResponseEntity<ApiResponseSuccess<Res>> create(Req req);

    ResponseEntity<ApiResponseSuccess<List<Res>>> getAllEntity();

    ResponseEntity<ApiResponseSuccess<Res>> getById(ID id);

    ResponseEntity<ApiResponseSuccess<String>> delete(ID id);

    ResponseEntity<String> update(ID id, Req req);
}
