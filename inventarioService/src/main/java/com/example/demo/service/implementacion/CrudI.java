package com.example.demo.service.implementacion;

import com.example.demo.repository.GenerarRepository;
import com.example.demo.service.CrudService;

import java.util.List;

public abstract class CrudI <T,ID> implements CrudService<T,ID> {
    protected abstract GenerarRepository<T,ID> getGenericRepo();

    @Override
    public T save(T t) {
        return getGenericRepo().save(t);
    }

    @Override
    public T update(T t) {
        return getGenericRepo().save(t);
    }

    @Override
    public T findById(ID id) {
        return getGenericRepo().findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return getGenericRepo().findAll();
    }

    @Override
    public void delete(ID id) {
        getGenericRepo().deleteById(id);
    }
}
