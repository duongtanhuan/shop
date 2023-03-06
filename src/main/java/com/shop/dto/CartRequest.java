package com.shop.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * CartRequest.
 * */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {
  private Integer id;
  
  private Integer customerId;
  
  private List<CartDetailRequest> cartDetails;
}
