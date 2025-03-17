package com.microservice.inventory.repositoiries.locations;


import com.microservice.inventory.models.locations.Container;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRepository extends JpaRepository<Container, String> {
}
