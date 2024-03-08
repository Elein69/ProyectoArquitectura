package com.example.demo.service;

import java.util.List;

public interface CrudService<T,ID> {
    T save(T t);
    T update(T t);
    T findById(ID id);
    List<T> findAll();
    void delete(ID id);
}
