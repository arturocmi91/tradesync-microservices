package com.microservice.inventory.models.locations;


import com.microservice.inventory.models.Improving.WarehouseSection;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "bin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Bin extends Location{

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private WarehouseSection warehouseSection;*/

   private String warehouseSection;

}
