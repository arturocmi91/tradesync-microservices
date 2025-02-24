package com.microservice.catalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Objects;

@Document(collection = "product")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id

    private  String id;

    @Indexed(unique = true)
    private String name;
    private double price;
    @JsonIgnoreProperties({ "area", "products"})
    @DBRef
    private Category category;


}

