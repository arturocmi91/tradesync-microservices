package com.microservice.catalog.dtos;

import com.microservice.catalog.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;

import java.util.List;

@Data
@Builder
@CompoundIndex(def = "{'name': 1, 'area': 1}", unique = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private  String id;
    private String name;
    private String area;

}
