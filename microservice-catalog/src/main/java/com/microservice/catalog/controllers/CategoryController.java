package com.microservice.catalog.controllers;

import com.microservice.catalog.dtos.CategoryDto;
import com.microservice.catalog.dtos.ProductDto;
import com.microservice.catalog.models.Category;
import com.microservice.catalog.models.Product;
import com.microservice.catalog.services.CategoryService;
import com.microservice.catalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {

        @Autowired
        CategoryService categoryService;

        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        public Category save(@RequestBody CategoryDto dto) throws IllegalAccessException {
            return categoryService.save(dto);
        }

        @GetMapping("/all")
        public ResponseEntity<List<Category>> getAllCategory() {
            return ResponseEntity.ok(categoryService.getAll());
        }

        @GetMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Category getCategory(@PathVariable String id) throws IllegalAccessException {
            return categoryService.getCategory(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Category> update(@PathVariable String id, @RequestBody CategoryDto dto) throws IllegalAccessException {
            return ResponseEntity.ok(categoryService.update(id, dto));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Category> delete(@PathVariable String id) {
            return ResponseEntity.ok(categoryService.delete(id));
        }



}
