package com.shop.service;

import com.shop.dto.CartRequestDTO;
import com.shop.dto.CartResponseDTO;

public interface ICartService {

    CartResponseDTO findCartByCustomerId(Integer customerId) throws Exception;

    CartResponseDTO addItemsToCart(CartRequestDTO dto) throws Exception;

    void updateItemsToCart(CartRequestDTO dto);

    void deleteItemInCartByCartDetailId(Integer cartDetailId);
}
