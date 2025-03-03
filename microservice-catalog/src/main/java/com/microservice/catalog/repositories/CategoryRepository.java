package com.microservice.catalog.repositories;

import com.microservice.catalog.models.Category;
import com.microservice.catalog.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {
    Optional<Category> findTopByOrderByIdDesc();
    Optional<Category> findByName(String name);


}
