package com.example.demo.repository;

import com.example.demo.entity.Producto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends GenerarRepository <Producto, Integer> {
}
