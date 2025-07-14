package com.example.quanlychitieuv2.mapper;

public interface BaseMapper <Req, Res, E> {

    E toEntity(Req req);
    Res toRes(E entity);
    void updateEntity(Req req,Res res);
}