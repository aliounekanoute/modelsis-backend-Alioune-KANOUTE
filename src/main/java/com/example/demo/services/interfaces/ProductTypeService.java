package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.models.ProductType;
import com.example.demo.services.dtos.ProductTypeDto;

public interface ProductTypeService {
    ProductType save(ProductTypeDto productTypeDto);
    List<ProductType> findAll();
    ProductType update(ProductTypeDto productTypeDto);
}
