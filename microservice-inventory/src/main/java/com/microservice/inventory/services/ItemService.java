package com.microservice.inventory.services;

import com.microservice.inventory.dtos.ItemDto;
import com.microservice.inventory.models.Item;
import com.microservice.inventory.repositoiries.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    public ItemRepository itemRepository;

    public Item getItem(Long id){
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This item " + id + " not found."));
    }
    public List<Item> getAllItems(){
        return  itemRepository.findAll();
    }
/*
    public Item save(ItemDto dto)  {

        if(itemRepository.existsById()){}

        Item item = Item.builder()
                .name(dto.getName())
                .brand(dto.getBrand())
                .barcode(dto.getBarcode())
                .supplier(dto.getSupplier())
                .description(dto.getDescription())
                .build();
        itemRepository.save(item);

        return item;
    }*/


}
