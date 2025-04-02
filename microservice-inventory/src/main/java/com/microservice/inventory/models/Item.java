package com.microservice.inventory.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties("items")
    //@JsonIgnoreProperties({ "items", "hibernateLazyInitializer", "handler" })

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id",nullable = false)
    private Supplier supplier;


}
