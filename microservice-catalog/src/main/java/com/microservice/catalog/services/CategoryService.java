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

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;


    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getCategory(String id) throws IllegalAccessException {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalAccessException("La categoría no existe"));
    }

    public Category save(CategoryDto dto) throws IllegalAccessException {
// Normalizar el nombre en minúsculas

        String formatName = formatCategory(dto.getName());

        // Verificar si ya existe una categoría con el mismo `formatName`
        Optional<Category> existingCategory = categoryRepository.findByName(formatName);
        if (existingCategory.isPresent()) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
        }
        String id = generateCatalogId(dto.getName(), dto.getArea());

        String CurrentName = formatCategory(dto.getName());

        Category category = new Category();
        category.setId(id);
        category.setName(formatName); // Guarda con el formato original
        category.setArea(formatCategory(dto.getArea()));
        category.setProducts(Collections.emptyList());


        return categoryRepository.save(category);
    }

    public Category update(String id, CategoryDto dto) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(" Categoría no encontrada."));

        String currentName = category.getName();

        String updatedName = formatCategory(dto.getName());


        if (!currentName.equalsIgnoreCase(updatedName)) {
            Optional<Category> existingNameCategory = categoryRepository.findByName(updatedName);


            if (existingNameCategory.isPresent()) {
                throw new IllegalArgumentException("Ya existe una categoría con ese nombre. Colocar otro!. ");
            }
            // Buscar todos los productos que tienen la categoría vieja
            List<Product> affectedProducts = productRepository.findByCategory(category);

            String newId = generateCatalogId(dto.getName(), dto.getArea());

            // Crear la nueva categoría
            Category newCategory = new Category();
            newCategory.setId(newId);
            newCategory.setName(updatedName);
            newCategory.setArea(formatCategory(dto.getArea()));
            newCategory.setProducts(affectedProducts);

            // Guardar la nueva categoría en la base de datos
            categoryRepository.save(newCategory);

            // Actualizar los productos para que usen la nueva categoría
            for (Product product : affectedProducts) {
                product.setCategory(newCategory);
                productRepository.save(product);

            }

            // Eliminar la categoría vieja
            categoryRepository.deleteById(id);

            return newCategory;

        }

        category.setName(updatedName); // Guarda con el formato original
        category.setArea(formatCategory(dto.getArea()));
        categoryRepository.save(category);


        return category;
    }


    public Category delete(String id) {
        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
        return category;
    }

    public void deleteAll() {
        List<Category> categories = categoryRepository.findAll();

        categoryRepository.deleteAll(categories);
    }

    //METODOS PRIVATE
    // Método para generar un catalogId único
    private String generateCatalogId(String name, String area) {
        String firstLetterName = name.substring(0, 1).toUpperCase();
        String firstLetterArea = area.substring(0, 1).toUpperCase();

        // Buscar el último catalogId generado y obtener el número secuencial
        Optional<Category> lastCategory = categoryRepository.findTopByOrderByIdDesc();
        int nextSequential = lastCategory.map(category -> {
            String lastId = category.getId();
            int lastNumber = Integer.parseInt(lastId.substring(3)); // Extrae el número
            return lastNumber + 1;
        }).orElse(1); // Si no hay categorías, comienza desde 1

        return "C" + firstLetterName + firstLetterArea + String.format("%03d", nextSequential);
    }

    // Método para formatear el nombre con la primera letra en mayúscula
    private String formatCategory(String String) {
        if (String == null || String.isEmpty()) {
            return "";
        }
        String = String.trim().toLowerCase(); // Convertir todo a minúscula primero
        return Character.toUpperCase(String.charAt(0)) + String.substring(1);
    }

}
