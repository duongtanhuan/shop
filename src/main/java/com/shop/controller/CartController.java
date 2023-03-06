package com.shop.controller;

import com.shop.dto.CartRequest;
import com.shop.dto.CartResponse;
import com.shop.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * CartController.
 * */
@RestController
@RequestMapping("carts")
public class CartController {
  @Autowired
  private ICartService service;
  
  @GetMapping(value = "/{customerId}")
  public ResponseEntity<CartResponse> findCartByCustomerId(@PathVariable Integer customerId) {
    return new ResponseEntity<>(service.findCartByCustomerId(customerId), HttpStatus.OK);
  }
  
  @PostMapping
  public ResponseEntity<CartResponse> addItemsToCart(@RequestBody CartRequest dto) {
    return new ResponseEntity<>(service.addItemsToCart(dto), HttpStatus.OK);
  }
  
  @PutMapping public ResponseEntity<HttpStatus> updateItemsToCart(@RequestBody CartRequest dto) {
    service.updateItemsToCart(dto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping(value = "/{customerId}")
  public ResponseEntity<HttpStatus> deleteItemInCartByCartDetailId(@PathVariable
                                                                     Integer customerId) {
    service.deleteItemInCartByCartDetailId(customerId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
