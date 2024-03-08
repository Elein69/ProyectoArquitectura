package com.example.demo.controller;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping(value = "/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService ;

    @GetMapping
    public ResponseEntity<List<Producto>> listProduct(@RequestParam(name = "categoriaId", required = false) Long categoriaId){
        List<Producto> productos = new ArrayList<>();
        if (null ==  categoriaId){
            productos = productoService.listAllProduct();
            if (productos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            productos = productoService.findByCategory(Categoria.builder().id(categoriaId).build());
            if (productos.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> getProduct(@PathVariable("id") Long id) {
        Producto producto =  productoService.getProduct(id);
        if (null==producto){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> createProduct(@Valid @RequestBody Producto producto, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Producto productCreate =  productoService.createProduct(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Producto> updateProduct(@PathVariable("id") Long id, @RequestBody Producto producto){
        producto.setId(id);
        Producto productoDB =  productoService.updateProduct(producto);
        if (productoDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> deleteProduct(@PathVariable("id") Long id){
        Producto productDelete = productoService.deleteProduct(id);
        if (productDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);
    }
    @PutMapping (value = "/{id}/stock")
    public ResponseEntity<Producto> updateStockProduct(@PathVariable  Long id ,@RequestParam(name = "cantidad", required = true) Double cantidad){
        Producto producto = productoService.updateStock(id, cantidad);
        if (producto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
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
