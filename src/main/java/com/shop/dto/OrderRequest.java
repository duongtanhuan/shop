package com.shop.dto;

import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * OrderRequest.
 * */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
  private Integer id;
  
  private Set<OrderDetailRequest> orderDetails;
  
  private Integer customerId;
  
  private Date createDate;
}
