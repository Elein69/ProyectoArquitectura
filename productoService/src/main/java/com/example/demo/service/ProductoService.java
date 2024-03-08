package com.example.demo.service;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> listAllProduct();
    public Producto getProduct(Long id);
    public Producto createProduct(Producto producto);
    public Producto updateProduct(Producto producto);
    public  Producto deleteProduct(Long id);
    public List<Producto> findByCategory(Categoria categoria);
    public Producto updateStock(Long id, Double cantidad);
}
