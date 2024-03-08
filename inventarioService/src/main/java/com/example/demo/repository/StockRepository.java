package com.example.demo.repository;

import com.example.demo.entity.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends GenerarRepository <Stock, Integer> {

}
