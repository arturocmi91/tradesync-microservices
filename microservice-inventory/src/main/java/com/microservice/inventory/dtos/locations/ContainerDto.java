package com.microservice.inventory.dtos.locations;

import com.microservice.inventory.models.Improving.DropZone;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.UniqueElements;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ContainerDto extends LocationDto {



    @NotBlank(message = "The dropzone is mandatory")
    @UniqueElements
    private String dropZone;



}

