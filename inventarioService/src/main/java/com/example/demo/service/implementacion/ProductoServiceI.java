package com.example.demo.service.implementacion;

import com.example.demo.entity.Producto;
import com.example.demo.repository.GenerarRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceI extends CrudI<Producto,Integer> implements ProductoService {
    @Autowired
    private ProductoRepository repo;
    @Override
    protected GenerarRepository<Producto, Integer> getGenericRepo() {
        return repo;
    }
    @Override
    public Producto findProductoNombre(String nombre) {
        return repo.findByProductoNombre(nombre); }
}
