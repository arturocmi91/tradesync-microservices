package com.microservice.inventory.dtos;

import com.microservice.inventory.enums.ItemStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDto {


    @NotBlank(message = "The item is mandatory")
    private String item;
    @NotBlank(message = "The location is mandatory")
    private String location;

    @Min(value = 1, message = "The quantity is mandatory")
    private int quantity;
    @NotNull(message = "The item status is mandatory")
    private ItemStatus status;
}
