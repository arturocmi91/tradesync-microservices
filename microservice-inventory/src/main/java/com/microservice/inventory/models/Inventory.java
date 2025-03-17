package com.microservice.inventory.models;

import com.microservice.inventory.enums.ItemStatus;

import com.microservice.inventory.models.locations.Location;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"item"})
@ToString(exclude = {"item"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
    @Id
    private String sku;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToMany
    @JoinTable(name = "inventory_location_table",
            joinColumns = @JoinColumn(name = "sku"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> locations;

    private int quantity;

    @Enumerated(EnumType.STRING)

    private ItemStatus status;
}
