package com.shop.dto;

import com.shop.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartResponseDTO {

    private Integer id;

    private Customer customer;

    private List<CartDetailDTO> cartDetails;

}
