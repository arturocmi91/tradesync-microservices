package com.microservice.inventory.services;

import com.microservice.inventory.dtos.ItemDto;
import com.microservice.inventory.enums.SupplierType;
import com.microservice.inventory.models.Item;
import com.microservice.inventory.models.Supplier;
import com.microservice.inventory.repositoiries.ItemRepository;
import com.microservice.inventory.repositoiries.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SupplierRepository supplierRepository;


    public Item getItem(Long id) throws IllegalArgumentException {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This item " + id + " not found."));
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item save(ItemDto dto) {// TODO :REVISAR PROBLEMA DE CREACION DE BARCODE REPETIDA EN EL TIPO DE SUPPLIER  (TIENE QUE SER UNICA)

        Supplier supplier = supplierRepository.findById(dto.getSupplier()).orElseThrow(()
                -> new IllegalArgumentException("This Supplier " + dto.getSupplier() + " not found."));

        // 1️⃣ Validación para INTERNAL_STORAGE (Debe ser único entre todos los Internal)
        if (supplier.getSupplierType() == SupplierType.INTERNAL_STORAGE) {
            if (itemRepository.existsByBarcodeAndSupplier_SupplierType(dto.getBarcode(), SupplierType.INTERNAL_STORAGE)) {
                throw new IllegalArgumentException("Barcode " + dto.getBarcode() + " already exists for an internal supplier.");

            }

            // 2️⃣ Validación para EXTERNAL_STORAGE (No se puede repetir en el mismo supplier)
        } else if (supplier.getSupplierType() == SupplierType.EXTERNAL_STORAGE) {
            if (itemRepository.existsByBarcodeAndSupplier_Id(dto.getBarcode(), dto.getSupplier())) {
                throw new IllegalArgumentException("Barcode " + dto.getBarcode() + " already exists for this external supplier.");
            }


        }


        Item item = Item.builder()
                .name(dto.getName())
                .brand(dto.getBrand())
                .barcode(dto.getBarcode())
                .supplier(supplier)
                .description(dto.getDescription())
                .build();
        itemRepository.save(item);

        return item;


    }

    public Item update(Long id, ItemDto dto) throws IllegalArgumentException {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));


        Supplier currentSupplier = item.getSupplier();

        // Verify if there's another item with the same barcode and supplier that's not this one
        // This is the key check you're asking for - to ensure the item ID matches the barcode+supplier combo
        Optional<Item> existingItem = itemRepository.findByBarcodeAndSupplierId(dto.getBarcode(),currentSupplier.getId());
        if (existingItem.isPresent() && !existingItem.get().getId().equals(id)) {
            log.warn("Item ID in path doesn't match with barcode+supplier combination");
            throw new IllegalArgumentException("Item ID " + id + " doesn't match with the item having barcode "
                    + dto.getBarcode() + " and supplier ID " + dto.getSupplier());
        }


        // Verifica si el código de barras ya existe en otro item con el mismo supplier
        if (currentSupplier.getSupplierType() == SupplierType.INTERNAL_STORAGE) {
            if (itemRepository.existsByBarcodeAndSupplier_SupplierTypeAndIdNot(dto.getBarcode(), SupplierType.INTERNAL_STORAGE, id)) {
                log.warn("take this way --");
                throw new IllegalArgumentException("Barcode " + dto.getBarcode() + " already exists for an internal supplier.");

            }
        } else if (currentSupplier.getSupplierType() == SupplierType.EXTERNAL_STORAGE) {
            if (itemRepository.existsByBarcodeAndSupplier_IdAndIdNot(dto.getBarcode(),currentSupplier.getId(),id)) {
                log.warn("take this way ++");
                throw new IllegalArgumentException("Barcode " + dto.getBarcode() + " already exists for this external supplier.");

            }
        }

        Supplier newSupplier = supplierRepository.findById(dto.getSupplier())
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));

        // Validación para el nuevo supplier si es diferente al actual
        if (!currentSupplier.getId().equals(newSupplier.getId())) {
            if (newSupplier.getSupplierType() == SupplierType.INTERNAL_STORAGE) {
                if (itemRepository.existsByBarcodeAndSupplier_SupplierType(dto.getBarcode(), SupplierType.INTERNAL_STORAGE)) {

                    throw new IllegalArgumentException("Barcode " + dto.getBarcode() + " already exists for an internal supplier.");
                }
            } else if (newSupplier.getSupplierType() == SupplierType.EXTERNAL_STORAGE) {
                if (itemRepository.existsByBarcodeAndSupplier_IdAndIdNot(dto.getBarcode(), newSupplier.getId(), id)) {

                    throw new IllegalArgumentException("Barcode " + dto.getBarcode() + " already exists for this external supplier.");
                }
            }
        }

        // Actualiza los datos del item
        item.setName(dto.getName());
        item.setBarcode(dto.getBarcode());
        item.setBrand(dto.getBrand());
        item.setSupplier(newSupplier);

        return itemRepository.save(item);
    }

    public Item delete(Long id) throws IllegalArgumentException {
        Item product = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        itemRepository.delete(product);
        return product;
    }

    public void deleteAll(){
     /*   List<Category>categories=categoryRepository.findAll();
        for (Category category: categories){
            category.setProducts(Collections.emptyList());
        }
        categoryRepository.saveAll(categories);*/
       itemRepository.deleteAll();
    }




}
