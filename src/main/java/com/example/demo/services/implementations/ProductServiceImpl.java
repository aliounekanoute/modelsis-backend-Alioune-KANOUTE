package com.example.demo.services.implementations;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Product;
import com.example.demo.models.ProductType;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ProductTypeRepository;
import com.example.demo.services.dtos.ProductDto;
import com.example.demo.services.interfaces.ProductService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    
    @Override
    public Product save(ProductDto productDto) {

        ModelMapper modelMapper = new ModelMapper();
        ProductType productType = findProductTypeByName(productDto.getTypeName());
        Product product = modelMapper.map(productDto, Product.class);
        product.setType(productType);

        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(UUID id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Product with id %s not found", id)
            ));
    }

    @Override
    public Product update(ProductDto productDto) {
        if(productDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id should not be null");
        }
        Product product = productRepository.findById(productDto.getId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Product with id %s not found", productDto.getId())
            ));

        ProductType productType = findProductTypeByName(productDto.getTypeName());

        product.setType(productType);
        product.setName(productDto.getName());

        return product;
    }

    private ProductType findProductTypeByName(String name) {
        return productTypeRepository
            .findByName(name)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Product type with name %s not found", name)
            ));
    }
    
}
