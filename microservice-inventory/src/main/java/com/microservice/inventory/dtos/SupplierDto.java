package com.microservice.inventory.dtos;


import com.microservice.inventory.enums.SupplierType;
import com.microservice.inventory.models.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDto {

    @NotBlank(message = "The name is mandatory")
    private String name;
    @NotBlank(message = "The type is mandatory")
    private SupplierType supplierType;


}
