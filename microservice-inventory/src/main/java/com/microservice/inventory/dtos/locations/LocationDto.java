package com.microservice.inventory.dtos.locations;

import com.microservice.inventory.dtos.InventoryDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.UniqueElements;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class LocationDto  {


    @NotBlank(message = "The code is mandatory")
    @UniqueElements
    private String code;

    private List<InventoryDto> inventories;
}
