package com.microservice.catalog.services.controllers;

import com.microservice.catalog.dtos.ProductDto;
import com.microservice.catalog.global.dto.MessageDto;
import com.microservice.catalog.global.exceptions.AttributeException;
import com.microservice.catalog.global.exceptions.ResourceNotFoundException;
import com.microservice.catalog.models.Product;
import com.microservice.catalog.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto save(@Validated @RequestBody ProductDto dto) throws ResourceNotFoundException, AttributeException {
        Product product= productService.save(dto);
        String message= "The Product: " + " ' " + product.getName() + " ' " + " have been created.";
        return new MessageDto(HttpStatus.CREATED,message);
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
    public ResponseEntity<MessageDto> update(@PathVariable String id,@Validated@RequestBody ProductDto dto) throws ResourceNotFoundException, AttributeException {
       Product product= productService.update(id,dto);
       String message= "The product: " + " ' " + product.getId() + " ' " + " have been updated";
       return ResponseEntity.ok(new MessageDto(HttpStatus.OK,message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable String id) throws ResourceNotFoundException {
        Product product= productService.delete(id);
        String message= "The product: " + " ' " +product.getName() + " ' " + " have been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK,message));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        productService.deleteAll();
    }


}
