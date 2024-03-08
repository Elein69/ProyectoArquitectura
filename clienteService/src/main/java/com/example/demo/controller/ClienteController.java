package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value="/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    // -------------------Recuperar un Cliente------------------------------------------

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable("id") long id) {
        log.info("Cliente encontrado con id {}", id);
        Cliente cliente = clienteService.obtenerCliente(id);
        if (  null == cliente) {
            log.error("Cliente con id {} no encontrado.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(cliente);
    }

    // -------------------Crear Cliente-------------------------------------------

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
        log.info("Crear Cliente : {}", cliente);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        Cliente clienteDB = clienteService.crearCliente (cliente);

        return  ResponseEntity.status( HttpStatus.CREATED).body(clienteDB);
    }

    // ------------------- Actualizar Cliente ------------------------------------------------

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
        log.info("Actualizar cliente con id {}", id);

        Cliente clienteActual = clienteService.obtenerCliente(id);

        if ( null == clienteActual ) {
            log.error("No se puede actualizar. Cliente con id {} no encontrado.", id);
            return  ResponseEntity.notFound().build();
        }
        clienteActual.setId(id);
        clienteActual=clienteService.actualizarCliente(cliente);
        return  ResponseEntity.ok(clienteActual);
    }

    // ------------------- Eliminar Cliente-----------------------------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> eliminarCliente(@PathVariable("id") long id) {
        log.info("Eliminar cliente con id {}", id);

        Cliente cliente = clienteService.obtenerCliente(id);
        if ( null == cliente ) {
            log.error("No se puede eliminar. Cliente con id {} no encontrado.", id);
            return  ResponseEntity.notFound().build();
        }
        cliente = clienteService.eliminarCliente(cliente);
        return  ResponseEntity.ok(cliente);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMensaje errorMessage = ErrorMensaje.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
