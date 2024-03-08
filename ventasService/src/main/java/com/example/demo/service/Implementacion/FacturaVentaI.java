package com.example.demo.service.Implementacion;

import com.example.demo.entity.FacturaVenta;
import com.example.demo.repository.FacturaVentaRepository;
import com.example.demo.repository.GenerarRepository;
import com.example.demo.service.FacturaVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaVentaI extends CrudServiceI<FacturaVenta, Integer> implements FacturaVentaService {
    @Autowired
    private FacturaVentaRepository repo;

    @Override
    protected GenerarRepository<FacturaVenta, Integer> getGenericRepo() {
        return repo;
    }

}
