package com.example.demo.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.models.Product;
import com.example.demo.services.dtos.ProductDto;

public interface ProductService {
    Product save(ProductDto productDto);
    List<Product> findAll();
    Product findById(UUID id);
    Product update(ProductDto productDto);
}
