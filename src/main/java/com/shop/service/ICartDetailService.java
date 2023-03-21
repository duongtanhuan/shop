package com.shop.service;

import com.shop.dto.CartDetailResponse;

/**
 * ICartDetailService.
 * */
public interface ICartDetailService {
  CartDetailResponse findCartDetailByCustomerIdAndItemId(Integer customerId, Integer itemId);
  
  CartDetailResponse findCartDetailById(Integer id);
}
