package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CartDetailRequest.
 * */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailRequest {
  private Integer id;
  
  private Integer itemId;
  
  private Integer quantity;
}
