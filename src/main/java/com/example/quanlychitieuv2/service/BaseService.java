package com.example.quanlychitieuv2.service;

import java.util.List;

public interface BaseService<Req, Res, ID> {
    List<Res> findAll();
    Res findById(ID id);
    Res create(Req dto);
    void update(ID id,Req dto);
    void deleteById(ID id);
}
