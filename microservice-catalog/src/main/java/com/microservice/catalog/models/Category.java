package com.microservice.catalog.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "category")
@Data
@CompoundIndex(def = "{'name': 1, 'area': 1}", unique = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Category {

    @Id
    private String id;

    @Indexed(unique = true) // Evita duplicados en MongoDB
    private String name;

    private String area;

    @JsonIgnoreProperties("category")

    private List<Product> products;



}
