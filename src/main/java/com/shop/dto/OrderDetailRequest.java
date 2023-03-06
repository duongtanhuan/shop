package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * OrderDetailRequest.
 * */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
  private Integer id;
  
  private Integer itemId;
  
  private Integer quantity;
}
