package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponseDTO {

    private Integer id;

    private OrderResponseDTO order;

    private ItemDTO item;

    private Integer quantity;
}
