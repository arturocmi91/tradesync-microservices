package com.microservice.inventory.models.locations;

import com.microservice.inventory.models.Improving.DropZone;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "container")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Container extends Location{



   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drop_zone_id")
    private DropZone dropZone;*/
   private String dropZone;



}

