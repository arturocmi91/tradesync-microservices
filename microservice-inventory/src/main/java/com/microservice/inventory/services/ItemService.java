package com.microservice.inventory.services;

import com.microservice.inventory.dtos.ItemDto;
import com.microservice.inventory.enums.SupplierType;
import com.microservice.inventory.models.Item;
import com.microservice.inventory.models.Supplier;
import com.microservice.inventory.repositoiries.ItemRepository;
import com.microservice.inventory.repositoiries.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Item save(ItemDto dto) {

        Supplier supplier = supplierRepository.findById(dto.getSupplier()).orElseThrow(()
                -> new IllegalArgumentException("This Supplier " + dto.getSupplier() + " not found."));

        // Verifica la unicidad del código de barras por tipo de proveedor
        if (supplier.getSupplierType() == SupplierType.INTERNAL_STORAGE) {
            if (itemRepository.existsByBarcodeAndSupplier_SupplierType(dto.getBarcode(), SupplierType.INTERNAL_STORAGE)) {
                throw new IllegalArgumentException("Barcode " + dto.getBarcode() + " already exists for an internal supplier.");

            }


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
   /*  public Item update(Long id, ItemDto dto) throws IllegalArgumentException  {




        Item item = itemRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Item not found"));

       if (ItemRepository.exist(dto.getBarcode()) && !itemRepository.findByName(dto.getName()).get().getId().equals(id)){
            throw  new AttributeException(" The name : " + dto.getName() + " already exist in another register ");
        }


        Category currentCategory= product.getCategory();

        Category newCategory = categoryRepository.findById(dto.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("This category " + dto.getCategory() + " not found."));



        if (currentCategory != null && !currentCategory.getId().equals(newCategory.getId())){
            currentCategory.getProducts().removeIf(p -> p.getId().equals(product.getId()));
            categoryRepository.save(currentCategory);
        }
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(newCategory);

        productRepository.save(product);

        setProductsIntoCategory(newCategory);

        return item;
    }*/



}
