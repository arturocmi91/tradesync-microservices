package com.microservice.catalog.controllers;

import com.microservice.catalog.dtos.ProductDto;
import com.microservice.catalog.global.exceptions.ResourceNotFoundException;
import com.microservice.catalog.models.Product;
import com.microservice.catalog.services.ProductService;
import jakarta.ws.rs.DELETE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody ProductDto dto) throws ResourceNotFoundException {
        return productService.save(dto);
    }

    @GetMapping("/all")

    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable String id) throws ResourceNotFoundException {
        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody ProductDto dto) throws  ResourceNotFoundException {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.delete(id));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        productService.deleteAll();
    }


}
