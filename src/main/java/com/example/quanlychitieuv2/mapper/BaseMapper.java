package com.example.quanlychitieuv2.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper <Req, Res, E> {

    E toEntity(Req req);
    Res toRes(E entity);
    E updateEntity(Req req,@MappingTarget E entity);
}
