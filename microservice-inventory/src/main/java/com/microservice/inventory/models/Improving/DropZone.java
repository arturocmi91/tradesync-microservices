package com.microservice.inventory.models.Improving;

import com.microservice.inventory.enums.DropZoneType;
import jakarta.persistence.*;
import lombok.*;

//@Entity
//@Table(name = "drop_zones")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DropZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DropZoneType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private WarehouseSection warehouseSection;



}
