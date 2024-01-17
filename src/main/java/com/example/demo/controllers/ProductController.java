package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Product;
import com.example.demo.services.dtos.ProductDto;
import com.example.demo.services.interfaces.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<Product> post(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productService.save(productDto));
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity
            .ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity
            .ok(productService.findById(id));
    }

    @PutMapping("")
    public ResponseEntity<Product> put(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity
            .ok(productService.update(productDto));
    }
}
