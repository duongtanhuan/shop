package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ItemRequest.
 * */
@Setter
@Getter
public class ItemRequest {
  private Integer id;
  
  private String name;
  
  private Double price;
}
