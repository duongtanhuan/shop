package com.shop.controller;

import com.shop.dto.ItemDTO;
import com.shop.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    private IItemService service;

    @GetMapping
    public ResponseEntity<?> getItems() throws Exception {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> findItemById(@PathVariable Integer itemId) throws Exception {
        return new ResponseEntity<>(service.findItemById(itemId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody ItemDTO dto) throws Exception {
        service.addItem(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody ItemDTO dto) throws Exception {
        service.updateItem(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer itemId) {
        service.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
