package com.shop.service;

import com.shop.dto.CartRequestDTO;
import com.shop.dto.CartResponseDTO;

public interface ICartService {

    CartResponseDTO findCartByCustomerId(Integer customerId);

    CartResponseDTO addItemsToCart(CartRequestDTO dto);

    void updateItemsToCart(CartRequestDTO dto);

    void deleteItemInCartByCartDetailId(Integer cartDetailId);
}
