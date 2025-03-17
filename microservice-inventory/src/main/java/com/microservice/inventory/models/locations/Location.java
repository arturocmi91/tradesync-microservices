package com.microservice.inventory.models.locations;

import com.microservice.inventory.models.Inventory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"inventories"})
@ToString(exclude = {"inventories"})
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Location {

    @Id
    private String id;

    @ManyToMany (mappedBy = "locations", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Inventory> inventories;

}
