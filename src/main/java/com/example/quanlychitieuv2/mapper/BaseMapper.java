package com.example.quanlychitieuv2.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface BaseMapper <Req, Res, E> {

    E toEntity(Req req);
    Res toRes(E entity);
    E updateEntity(Req req,E entity);
}
