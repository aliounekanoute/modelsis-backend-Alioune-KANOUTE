package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ProductType;
import com.example.demo.services.dtos.ProductTypeDto;
import com.example.demo.services.interfaces.ProductTypeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/productType")
@AllArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @PostMapping("")
    public ResponseEntity<ProductType> post(@Valid @RequestBody ProductTypeDto productTypeDto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productTypeService.save(productTypeDto));
    }

    @GetMapping("")
    public ResponseEntity<List<ProductType>> getAll() {
        return ResponseEntity
            .ok(productTypeService.findAll());
    }

    @PutMapping("")
    public ResponseEntity<ProductType> put(@Valid @RequestBody ProductTypeDto productTypeDto) {
        return ResponseEntity
            .ok(productTypeService.update(productTypeDto));
    }
}
