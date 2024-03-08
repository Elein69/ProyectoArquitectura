package com.example.demo.repository;

import com.example.demo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenerarRepository <T,ID> extends JpaRepository<T,ID> {
    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombreProducto")
    Producto findByProductoNombre(String nombreProducto);
}
