package com.example.demo.service.implementacion;

import com.example.demo.entity.Categoria;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.GenerarRepository;
import com.example.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceI extends CrudI<Categoria,Integer> implements CategoriaService {
    @Autowired
    private CategoriaRepository repo;
    @Override
    protected GenerarRepository<Categoria, Integer> getGenericRepo() {
        return repo;
    }
}
