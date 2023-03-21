package com.shop.controller;

import com.shop.dto.CartDetailResponse;
import com.shop.dto.CartResponse;
import com.shop.service.ICartDetailService;
import com.shop.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CartController.
 * */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("cartDetails")
public class CartDetailController {
  
  @Autowired
  private ICartDetailService service;
  
  @GetMapping(value = "/{cartId}/{itemId}")
  public ResponseEntity<CartDetailResponse> findCartDetailByCartIdAndItemId(@PathVariable Integer cartId, @PathVariable Integer itemId) {
    return new ResponseEntity<>(service.findCartDetailByCustomerIdAndItemId(cartId, itemId), HttpStatus.OK);
  }
  
  @GetMapping(value = "/{cartDetailId}")
  public ResponseEntity<CartDetailResponse> findCartDetailById(@PathVariable Integer cartDetailId) {
    return new ResponseEntity<>(service.findCartDetailById(cartDetailId), HttpStatus.OK);
  }
}
