package com.microservice.inventory.dtos.locations;


import com.microservice.inventory.models.Improving.WarehouseSection;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.UniqueElements;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BinDto extends LocationDto {

    @NotBlank(message = "The section is mandatory")
    @UniqueElements
    private String warehouseSection;


}
