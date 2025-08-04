package com.microservice.inventory.services;

import com.microservice.inventory.dtos.SupplierDto;
import com.microservice.inventory.models.Supplier;
import com.microservice.inventory.repositoiries.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;


    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(Long id) throws IllegalArgumentException {
        return supplierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "This supplier " + id + " doesn't exist "));
    }

    public Supplier saveSupplier(SupplierDto dto) throws IllegalAccessException {

        if (supplierRepository.existByName(dto.getName())) {
            throw new IllegalAccessException("This supplier name: " + dto.getName() + "already exist");
        }

        return Supplier.builder().
                name(dto.getName()).
                supplierType(dto.getSupplierType()).
                build();
    }

    public Supplier updateSupplier(Long id, SupplierDto dto) throws IllegalAccessException {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "This supplier " + id + " doesn't exist "));
        if (supplierRepository.existByName(dto.getName())
                && !supplierRepository.findByName(dto.getName()).get().getId().equals(id)) {

            throw new IllegalAccessException("This supplier : " + dto.getName() + "already exist in another register");

        }
        supplier.setName(dto.getName());
        supplier.setSupplierType(dto.getSupplierType());

        return  supplier;
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }


}
