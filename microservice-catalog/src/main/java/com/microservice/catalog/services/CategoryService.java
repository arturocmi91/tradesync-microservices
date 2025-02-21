package com.microservice.catalog.services;

import com.microservice.catalog.dtos.CategoryDto;
import com.microservice.catalog.dtos.ProductDto;
import com.microservice.catalog.models.Category;
import com.microservice.catalog.models.Product;
import com.microservice.catalog.repositories.CategoryRepository;
import com.microservice.catalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getCategory(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalAccessException("La categoría no existe"));
    }

    public Category save(CategoryDto dto) throws IllegalAccessException {



        Category category = new Category(dto.getId(), dto.getName(), dto.getArea(),null);
        return categoryRepository.save(category);
    }

    public Product update(String id, ProductDto dto) throws IllegalAccessException {

        Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
        if (category.isEmpty()) {
            throw new IllegalAccessException("La categoría con ID " + dto.getCategoryId() + " no existe.");
        }

        Product product = productRepository.findById(id).get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(category.get());

        return productRepository.save(product);
    }

    public Product delete(String id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return product;
    }
}
