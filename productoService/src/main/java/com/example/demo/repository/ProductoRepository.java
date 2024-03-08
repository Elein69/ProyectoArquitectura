package com.example.demo.repository;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto,Long> {
    public List<Producto> findByCategory(Categoria category);
}
