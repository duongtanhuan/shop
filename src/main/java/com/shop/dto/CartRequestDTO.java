package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDTO {

    private Integer id;

    private Integer customerId;

    private List<CartDetailDTO> cartDetails;
}
