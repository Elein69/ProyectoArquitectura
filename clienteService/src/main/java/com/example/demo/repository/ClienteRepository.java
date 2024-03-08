package com.example.demo.repository;

import com.example.demo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    public Cliente buscarPorCedula(String Cedula);
    public List<Cliente> buscarPorApellido(String Apellido);

}
