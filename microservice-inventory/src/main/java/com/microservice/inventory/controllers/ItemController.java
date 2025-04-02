package com.microservice.inventory.controllers;

import com.microservice.inventory.dtos.ItemDto;
import com.microservice.inventory.models.Item;
import com.microservice.inventory.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody ItemDto dto) throws IllegalAccessException {

        try {
            Item savedItem = itemService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
   /*public MessageDto save(@Validated @RequestBody ProductDto dto) throws ResourceNotFoundException, AttributeException {
       Item item= itemService.save(dto);
        String message= "The Product: " + " ' " + product.getName() + " ' " + " have been created.";
        return new MessageDto(HttpStatus.CREATED,message);
    }*/


    @GetMapping("/all")

   public ResponseEntity<List<Item>> getAllProduct() {
        return ResponseEntity.ok(itemService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Item getProduct(@PathVariable Long id) throws IllegalArgumentException {
        return itemService.getItem(id);
    }
/*
    @PutMapping("/{id}")

   public ResponseEntity<MessageDto> update(@PathVariable String id, @Validated @RequestBody ProductDto dto) throws ResourceNotFoundException, AttributeException {
        Product product = productService.update(id, dto);
        String message = "The product: " + " ' " + product.getId() + " ' " + " have been updated";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable String id) throws ResourceNotFoundException {
        Product product = productService.delete(id);
        String message = "The product: " + " ' " + product.getName() + " ' " + " have been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        productService.deleteAll();
    }*/
}
