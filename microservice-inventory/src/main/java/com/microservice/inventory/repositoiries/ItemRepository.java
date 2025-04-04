package com.microservice.inventory.repositoiries;

import com.microservice.inventory.enums.SupplierType;
import com.microservice.inventory.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {



    boolean existsByBarcode(String barcode);
    // Verifica si ya existe un código de barras en cualquier proveedor interno
    boolean existsByBarcodeAndSupplier_SupplierType(String barcode, SupplierType supplierType);

    // Verifica si ya existe un código de barras, nombre y marca en el mismo proveedor externo
    boolean existsByBarcodeAndNameAndBrandAndSupplier_Id(String barcode, String name, String brand, Long supplierId);


    boolean existsByBarcodeAndSupplier_SupplierTypeAndIdNot(String barcode, SupplierType supplierType, Long id);

    boolean existsByBarcodeAndSupplier_IdAndIdNot(String barcode, Long supplierId, Long id) ;





    Optional<Item> findByBarcodeAndSupplierId(String barcode, Long supplier);

    boolean existsByBarcodeAndSupplier_Id(String barcode, Long supplier);
}
