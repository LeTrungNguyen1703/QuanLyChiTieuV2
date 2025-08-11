package com.example.quanlychitieuv2.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface BaseMapper <Req, Res, E> {

    E toEntity(Req req);
    Res toRes(E entity);
    List<Res> toListRes(List<E> entityList);
    E updateEntity(Req req,E entity);

}
