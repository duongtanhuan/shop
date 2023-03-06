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
public class OrderDetailResponse {
  private Integer id;
  
  private ItemResponse item;
  
  private Integer quantity;
}
