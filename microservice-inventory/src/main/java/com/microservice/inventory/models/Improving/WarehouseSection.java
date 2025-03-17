package com.microservice.inventory.models.Improving;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@Entity
//@Table(name = "warehouse_sections")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "warehouseSection", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DropZone> dropZones;


}