package com.microservice.catalog.dtos;

import com.microservice.catalog.models.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.index.Indexed;


import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank(message = "The product name is mandatory")
    @Indexed(unique = true)

    private String name;
    @Min(value = 1,message = "The price is mandatory")
    private double price;

    @NotBlank(message = "The category name is mandatory")
    private String category;


}