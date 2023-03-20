package com.shop.controller;

import com.shop.dto.ItemRequest;
import com.shop.dto.ItemResponse;
import com.shop.service.IItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ItemController.
 **/
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("items")
public class ItemController {
  
  @Autowired
  private IItemService service;
  
  @GetMapping
  public ResponseEntity<List<ItemResponse>> getItems() {
    return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
  }
  
  @GetMapping("/{itemId}")
  public ResponseEntity<ItemResponse> findItemById(@PathVariable Integer itemId) {
    return new ResponseEntity<>(service.findItemById(itemId), HttpStatus.OK);
  }
  
  @PostMapping
  public ResponseEntity<HttpStatus> addItem(@RequestBody ItemRequest dto) {
    service.addItem(dto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
  
  @PutMapping
  public ResponseEntity<HttpStatus> updateItem(@RequestBody ItemRequest dto) {
    service.updateItem(dto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @DeleteMapping(value = "/{itemId}")
  public ResponseEntity<HttpStatus> deleteItem(@PathVariable Integer itemId) {
    service.deleteItem(itemId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
}
