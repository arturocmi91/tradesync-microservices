package com.microservice.inventory.models;


import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "item")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcode;

    private String name;

    private String brand;

    private String description;


}
