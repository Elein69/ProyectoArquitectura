package com.example.demo.service;

import com.example.demo.entity.Producto;

public interface ProductoService extends CrudService<Producto,Integer> {
    Producto findProductoNombre(String nombreProducto);
}
