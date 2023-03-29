package com.shop.service;

import com.shop.dto.OrderRequest;
import com.shop.dto.OrderResponse;
import java.util.List;

/**
 * IOrderService.
 * */
public interface IOrderService {
  List<OrderResponse>  getOrdersAllByCustomerId(Integer customerId);
  
  List<OrderResponse>  getPendingOrdersByStatus();
  
  void createOrder(OrderRequest request);
  
  OrderResponse getLatestOrderByCustomerId(Integer customerId);
}
