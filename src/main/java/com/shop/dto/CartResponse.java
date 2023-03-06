package com.shop.dto;

import com.shop.mapper.CartDetailMapper;
import com.shop.mapper.CustomerMapper;
import com.shop.model.Cart;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


/**
 * CartResponse.
 * */
@Setter
@Getter
public class CartResponse {
  private Integer id;
  private CustomerResponse customer;
  
  private List<CartDetailResponse> cartDetails;
  
  /**
   * CartResponse.
   * */
  public CartResponse(Cart cart) {
    this.id = cart.getId();
    this.customer = CustomerMapper.INSTANCE.toDto(cart.getCustomer());
    this.cartDetails = CartDetailMapper.INSTANCE.toDtoList(cart.getCartDetails());
  }
}
