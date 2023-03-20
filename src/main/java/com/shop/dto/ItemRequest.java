package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ItemRequest.
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
  private Integer id;
  
  private String name;
  
  private Double price;
  
  public ItemRequest(String name, Double price) {
    this.name = name;
    this.price = price;
  }
}
