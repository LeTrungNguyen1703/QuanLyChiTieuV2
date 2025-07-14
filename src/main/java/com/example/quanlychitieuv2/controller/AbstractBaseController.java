package com.example.quanlychitieuv2.controller;

import com.example.quanlychitieuv2.dto.ApiResponseSuccess;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
public abstract class AbstractBaseController<Req, Res, E, ID> implements BaseController<Req, Res, ID> {

    protected abstract AbstractBaseService<Req, Res, E, ID> abstractService();

    @Override
    @PostMapping
    public ResponseEntity<ApiResponseSuccess<Res>> create(@RequestBody Req req) {
        Res response = abstractService().create(req);
        return ResponseEntity.ok(new ApiResponseSuccess<>(response));
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponseSuccess<List<Res>>> getAllEntity() {
        List<Res> listResponse = abstractService().findAll();
        return ResponseEntity.ok(new ApiResponseSuccess<>(listResponse));
    }

    @Override
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponseSuccess<Res>> getById(@PathVariable ID id) {
        Res response = abstractService().findById(id);
        return ResponseEntity.ok(new ApiResponseSuccess<>(response));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccess<String>> delete(@PathVariable ID id) {
        abstractService().deleteById(id);
        return ResponseEntity.ok(new ApiResponseSuccess<>("Delete Successfully"));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable ID id, @RequestBody Req req) {
        abstractService().update(id, req);
        return ResponseEntity.ok("Update Successfully");
    }
}
