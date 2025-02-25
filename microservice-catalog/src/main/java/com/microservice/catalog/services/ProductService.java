package com.microservice.catalog.services;

import com.microservice.catalog.dtos.ProductDto;
import com.microservice.catalog.global.exceptions.AttributeException;
import com.microservice.catalog.global.exceptions.ResourceNotFoundException;
import com.microservice.catalog.models.Category;
import com.microservice.catalog.models.Product;
import com.microservice.catalog.repositories.CategoryRepository;
import com.microservice.catalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getProduct(String id) throws ResourceNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This product " + id + " not found."));
    }

    public Product save(ProductDto dto) throws ResourceNotFoundException, AttributeException {

        Optional<Category> categoryOptional = categoryRepository.findById(dto.getCategoryId());
        if (categoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("This category " + dto.getCategoryId() + " not found.");
        }
        if (productRepository.existsByName(dto.getName())){
            throw  new AttributeException(" This name "+dto.getName()+" already exist ");
        }

        Category category= categoryOptional.get();

        String id = UUID.randomUUID().toString(); // Generar UUID

        Product product = new Product(id, dto.getName(), dto.getPrice(), category);
        productRepository.save(product);
        setProductsIntoCategory(category);
        return product;
    }

    public Product update(String id, ProductDto dto) throws ResourceNotFoundException, AttributeException {




        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (productRepository.existsByName(dto.getName()) && !productRepository.findByName(dto.getName()).get().getId().equals(id)){
            throw  new AttributeException(" The name : " + dto.getName() + " already exist in another register ");
        }


        Category currentCategory= product.getCategory();

        Category newCategory = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("This category " + dto.getCategoryId() + " not found."));



        if (currentCategory != null && !currentCategory.getId().equals(newCategory.getId())){
            currentCategory.getProducts().removeIf(p -> p.getId().equals(product.getId()));
            categoryRepository.save(currentCategory);
        }
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(newCategory);

        productRepository.save(product);

        setProductsIntoCategory(newCategory);

        return product;
    }


    public Product delete(String id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        productRepository.delete(product);
        return product;
    }

    public void deleteAll(){
        List<Category>categories=categoryRepository.findAll();
        for (Category category: categories){
            category.setProducts(Collections.emptyList());
        }
        categoryRepository.saveAll(categories);
        productRepository.deleteAll();
    }

    //METODOS PRIVATE
    // Método para formatear el nombre con la primera letra en mayúscula
    private String formatString(String String) {
        if (String == null || String.isEmpty()) {
            return "";
        }
        String = String.trim().toLowerCase(); // Convertir todo a minúscula primero
        return Character.toUpperCase(String.charAt(0)) + String.substring(1);
    }
    // Método para settear products de category
    private void setProductsIntoCategory(Category category){
        List<Product> products = productRepository.findByCategory(category);
        category.setProducts(products);
        categoryRepository.save(category);

    }


    // Método para formatear el nombre con la primera letra en mayúscula
    private String formatForProducts(String String) {
        if (String == null || String.isEmpty()) {
            return "";
        }
        String = String.trim().toLowerCase(); // Convertir todo a minúscula primero
        return Character.toUpperCase(String.charAt(0)) + String.substring(1);
    }


}
