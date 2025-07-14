package com.example.quanlychitieuv2.service;

import com.example.quanlychitieuv2.exception.ResourceNotFound;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Slf4j
public abstract class AbstractBaseService<Req, Res, E, ID> implements BaseService<Req, Res, ID> {

    protected JpaRepository<E, ID> jpaRepository;

    protected abstract BaseMapper<Req, Res, E> getMapper();

    public AbstractBaseService(JpaRepository<E, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<Res> findAll() {
        List<E> listEntity = jpaRepository.findAll();

        return listEntity.stream().map(getMapper()::toRes).toList();

    }

    public Res findById(ID id) {
        E entity = jpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Entity not found with id " + id));
        return getMapper().toRes(entity);
    }

    public Res create(Req req) {
        E entity = getMapper().toEntity(req);
        entity = jpaRepository.save(entity);
        return getMapper().toRes(entity);
    }

    public void update(ID id, Req req) {
        E entity = getMapper().toEntity(req);
        jpaRepository.save(entity);
    }

    public void deleteById(ID id) {
        jpaRepository.deleteById(id);
    }

}
