package com.example.demo.services.implementations;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.ProductType;
import com.example.demo.repositories.ProductTypeRepository;
import com.example.demo.services.dtos.ProductTypeDto;
import com.example.demo.services.interfaces.ProductTypeService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    
    @Override
    public ProductType save(ProductTypeDto productTypeDto) {
        ModelMapper modelMapper = new ModelMapper();
        ProductType productType = modelMapper.map(productTypeDto, ProductType.class);
        return productTypeRepository.save(productType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType update(ProductTypeDto productTypeDto) {
        if(productTypeDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id should not be null");
        }
        ProductType productType = productTypeRepository
            .findById(productTypeDto.getId())
            .orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    String.format("Product type with id %s not found", productTypeDto.getId())
                )
            );

        productType.setName(productTypeDto.getName());

        return productType;
    }
    
}
