package com.example.demo.service;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceI implements  ProductoService {

    private final ProductoRepository productoRepository;
    @Override
    public List<Producto> listAllProduct() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProduct(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto createProduct(Producto producto) {
        producto.setEstado("Creado");
        producto.setCreateAt(new Date());

        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProduct(Producto producto) {
        Producto productoDB = getProduct(producto.getId());
        if (null == productoDB){
            return null;
        }
        productoDB.setNombre(producto.getNombre());
        productoDB.setDescripcion(producto.getDescripcion());
        productoDB.setCategoria(producto.getCategoria());
        productoDB.setPrecio(producto.getPrecio());
        return productoRepository.save(productoDB);
    }

    @Override
    public Producto deleteProduct(Long id) {
        Producto productoDB = getProduct(id);
        if (null == productoDB){
            return null;
        }
        productoDB.setEstado("DELETED");
        return productoRepository.save(productoDB);
    }

    @Override
    public List<Producto> findByCategory(Categoria categoria) {
        return productoRepository.findByCategory(categoria);
    }

    @Override
    public Producto updateStock(Long id, Double cantidad) {
        Producto productoDB = getProduct(id);
        if (null == productoDB){
            return null;
        }
        Double stock =  productoDB.getStock() + cantidad;
        productoDB.setStock(stock);
        return productoRepository.save(productoDB);
    }
}
