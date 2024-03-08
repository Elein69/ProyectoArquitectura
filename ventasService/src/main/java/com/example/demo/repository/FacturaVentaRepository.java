package com.example.demo.repository;

import com.example.demo.entity.FacturaVenta;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaVentaRepository <T,ID> extends GenerarRepository<FacturaVenta,Integer>  {
}
