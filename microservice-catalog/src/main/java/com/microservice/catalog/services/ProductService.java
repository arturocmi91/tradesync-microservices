package com.microservice.catalog.services;

import com.microservice.catalog.dtos.ProductDto;
import com.microservice.catalog.models.Category;
import com.microservice.catalog.models.Product;
import com.microservice.catalog.repositories.CategoryRepository;
import com.microservice.catalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getProduct(String id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product save(ProductDto dto) throws IllegalAccessException {

        Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
        if (category.isEmpty()) {
            throw new IllegalAccessException("La categoría con ID " + dto.getCategoryId() + " no existe.");
        }

        String id = UUID.randomUUID().toString(); // Generar UUID

        Product product = new Product(id, dto.getName(), dto.getPrice(), category.get());
        return productRepository.save(product);
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
