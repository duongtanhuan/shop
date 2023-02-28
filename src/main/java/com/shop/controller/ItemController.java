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
    public ResponseEntity<?> getItems() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> findItemById(@PathVariable Integer itemId) {
        return new ResponseEntity<>(service.findItemById(itemId), HttpStatus.OK);
    }

    @PostMapping
    public void addItem(@RequestBody ItemDTO dto) {
        service.addItem(dto);
    }

}
