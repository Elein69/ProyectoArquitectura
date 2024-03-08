package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClienteServiceI implements  ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        Cliente clienteDB = clienteRepository.buscarPorCedula ( cliente.getCedula () );
        if (clienteDB != null){
            return  clienteDB;
        }

        cliente.setState("Cliente Creado");
        clienteDB = clienteRepository.save ( cliente );
        return clienteDB;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        Cliente clienteDB = obtenerCliente(cliente.getId());
        if (clienteDB == null){
            return  null;
        }
        clienteDB.setNombre(cliente.getNombre());
        clienteDB.setApellido(cliente.getApellido());
        clienteDB.setEmail(cliente.getEmail());

        return  clienteRepository.save(clienteDB);
    }

    @Override
    public Cliente eliminarCliente(Cliente cliente) {
        Cliente clienteDB = obtenerCliente(cliente.getId());
        if (clienteDB ==null){
            return  null;
        }
        cliente.setState("Cliente Eliminado");
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerCliente(Long id) {
        return  clienteRepository.findById(id).orElse(null);
    }
}
