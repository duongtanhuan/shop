package com.shop.controller;

import com.shop.dto.OrderRequest;
import com.shop.dto.OrderResponse;
import com.shop.service.IOrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController.
 * */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("orders")
public class OrderController {
  
  @Autowired
  private IOrderService service;
  
  @GetMapping(value = "/{customerId}")
  public ResponseEntity<List<OrderResponse>> getOrderAllByCustomerId(@PathVariable
                                                                       Integer customerId) {
    return new ResponseEntity<>(service.getOrdersAllByCustomerId(customerId), HttpStatus.OK);
  }
  
  @PostMapping
  public ResponseEntity<HttpStatus> createOrder(@RequestBody OrderRequest dto) {
    service.createOrder(dto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @PostMapping (value = "latestOrder/{customerId}")
  public ResponseEntity<OrderResponse> getLatestOrderByCustomerId(@PathVariable
                                                                    Integer customerId) {
    return new ResponseEntity<>(service.getLatestOrderByCustomerId(customerId), HttpStatus.OK);
  }
}
