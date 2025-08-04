package com.microservice.inventory.repositoiries;

import com.microservice.inventory.enums.SupplierType;
import com.microservice.inventory.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Boolean existByName(String name);
    Optional<Supplier> findByName(String name);
}
