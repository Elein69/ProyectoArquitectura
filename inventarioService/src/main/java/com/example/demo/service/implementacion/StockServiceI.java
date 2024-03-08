package com.example.demo.service.implementacion;

import com.example.demo.entity.Stock;
import com.example.demo.repository.GenerarRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceI extends CrudI<Stock,Integer> implements StockService {
    @Autowired
    private StockRepository repo;
    @Override
    protected GenerarRepository<Stock, Integer> getGenericRepo() {
        return repo;
    }

}
