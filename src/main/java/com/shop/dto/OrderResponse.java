package com.shop.dto;

import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * OrderResponse.
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
  private Integer id;
  
  private double totalPrice;
  
  private CustomerResponse customer;
  
  private Boolean status;
  
  private Set<OrderDetailResponse> orderDetails;
  
  Date createDate;
}
