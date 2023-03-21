package com.shop.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CartDetailResponse.
 * */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailResponse {
  private Integer id;
  
  private ItemResponse item;
  
  private Integer quantity;
  
  private Integer itemId;
  
  private Date dateAdded;
}
