package com.example.demo.service;

import com.example.demo.entity.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> buscarClientes();

    public Cliente crearCliente(Cliente cliente);
    public Cliente actualizarCliente(Cliente cliente);
    public Cliente eliminarCliente(Cliente cliente);
    public  Cliente obtenerCliente(Long id);
}

