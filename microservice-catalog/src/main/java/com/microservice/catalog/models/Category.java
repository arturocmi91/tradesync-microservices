package com.microservice.catalog.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Category {

    @Id
    @Indexed(unique = true)
    private String id;
    private String name;
    private String area;
    @DBRef
    private List<Product> products;



}
