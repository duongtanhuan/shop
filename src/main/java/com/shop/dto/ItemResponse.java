package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ItemResponse.
 * */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
  private Integer id;
  
  private String name;
  
  private Double price;
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ItemDTO{");
    sb.append("id=").append(id);
    sb.append(",name=").append(name);
    sb.append(",price=").append(price);
    sb.append('}');
    return sb.toString();
  }
}
