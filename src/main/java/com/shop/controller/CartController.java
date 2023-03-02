package com.shop.controller;

import com.shop.dto.CartRequestDTO;
import com.shop.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carts")
public class CartController {
    @Autowired
    private ICartService service;

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<?> findCartByCustomerId(@PathVariable Integer customerId) {
        return new ResponseEntity<>(service.findCartByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addItemsToCart(@RequestBody CartRequestDTO dto) {
        return new ResponseEntity<>(service.addItemsToCart(dto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateItemsToCart(@RequestBody CartRequestDTO dto) {
        service.updateItemsToCart(dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<?> deleteItemInCartByCartDetailId(@PathVariable Integer customerId) {
        service.deleteItemInCartByCartDetailId(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
