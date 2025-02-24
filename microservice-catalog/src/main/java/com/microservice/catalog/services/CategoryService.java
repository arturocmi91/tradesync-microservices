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

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getCategory(String id) throws IllegalAccessException {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalAccessException("La categoría no existe"));
    }

    public Category save(CategoryDto dto) throws IllegalAccessException {
// Normalizar el nombre en minúsculas

        String formatName =formatName(dto.getName());



        // Verificar si ya existe una categoría con el mismo `normalizedName`
        Optional<Category> existingCategory = categoryRepository.findByName(formatName);
        if (existingCategory.isPresent()) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
        }


        String id= generateCatalogId(dto.getName(), dto.getArea());

        Category category = new Category();
       category.setId(id);
        category.setName(formatName); // Guarda con el formato original
        category.setArea(dto.getArea());
       category.setProducts(Collections.emptyList());

                ;
        return categoryRepository.save(category);
    }

    public Category update(String id, CategoryDto dto)  {


        Category category = categoryRepository.findById(id).get();

        category.setName(dto.getName());
        category.setArea(dto.getArea());


        return categoryRepository.save(category);
    }

    public Category delete(String id) {
        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
        return category;
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
    private String formatName(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        name = name.trim().toLowerCase(); // Convertir todo a minúscula primero
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

}
