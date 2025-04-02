package com.microservice.inventory.repositoiries;

import com.microservice.inventory.enums.SupplierType;
import com.microservice.inventory.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long > {

    List<Supplier> findBySupplierType(SupplierType supplierType);
}
