package com.microservice.inventory.repositoiries;
import com.microservice.inventory.enums.SupplierType;
import com.microservice.inventory.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    Optional<Item> findByName(String name);
    boolean existsByName(String name);
    Optional<Item> findByBarcode(String barcode);

    // Verifica si ya existe un código de barras en cualquier proveedor interno
    boolean existsByBarcodeAndSupplier_SupplierType(String barcode, SupplierType supplierType);

    // Verifica si ya existe un código de barras en el mismo proveedor externo
    boolean existsByBarcodeAndSupplier_Id(String barcode, Long supplierId);

}
