package com.shop.dto;

import com.shop.mapper.CartDetailMapper;
import com.shop.mapper.CustomerMapper;
import com.shop.model.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartResponseDTO {

    private Integer id;
    private CustomerDTO customer;

    private List<CartDetailResponseDTO> detailDTOS;

    public CartResponseDTO(Cart cart) {
        this.id = cart.getId();
        this.customer = CustomerMapper.INSTANCE.toDto(cart.getCustomer());
        this.detailDTOS = CartDetailMapper.INSTANCE.toDtoList(cart.getCartDetails());
    }
}
