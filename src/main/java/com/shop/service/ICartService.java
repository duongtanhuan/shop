package com.shop.service;

import com.shop.dto.CartRequest;
import com.shop.dto.CartResponse;

/**
 * ICartService.
 * */
public interface ICartService {
  CartResponse findCartByCustomerId(Integer customerId);
  
  CartResponse addItemsToCart(CartRequest request);
  
  void updateItemsToCart(CartRequest request);
  
  void deleteItemInCartByCartDetailId(Integer cartDetailId);
}
