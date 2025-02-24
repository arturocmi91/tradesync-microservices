package com.microservice.catalog.repositories;

import com.microservice.catalog.models.Category;
import com.microservice.catalog.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByCategory(Category category);

}
