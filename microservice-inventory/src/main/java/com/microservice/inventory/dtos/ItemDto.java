package com.microservice.inventory.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {


    @NotBlank(message = "The barcode is mandatory")
    private String barcode;
    @NotBlank(message = "The name is mandatory")
    private String name;
    @NotBlank(message = "The brand is mandatory")
    private String brand;
    @NotBlank(message = "The brand is mandatory")
    private String description;

}
