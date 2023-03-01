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
    public ResponseEntity<?> findCartByCustomerId(@PathVariable Integer customerId) throws Exception {
        return new ResponseEntity<>(service.findCartByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addItemsToCart(@RequestBody CartRequestDTO dto) throws Exception {
        return new ResponseEntity<>(service.addItemsToCart(dto), HttpStatus.OK);
    }

}
