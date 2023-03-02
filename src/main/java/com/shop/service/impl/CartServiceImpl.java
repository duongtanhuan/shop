package com.shop.service.impl;

import com.shop.dto.CartRequestDTO;
import com.shop.dto.CartResponseDTO;
import com.shop.exception.CartDetailNotFoundException;
import com.shop.exception.CartNotFoundException;
import com.shop.exception.ItemNotFoundException;
import com.shop.exception.QuantityLessThanOneException;
import com.shop.model.Cart;
import com.shop.model.CartDetail;
import com.shop.model.Item;
import com.shop.repository.CartDetailRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public CartResponseDTO findCartByCustomerId(Integer customerId) {
        try {
            Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
            if (cart.isPresent()) {
                return new CartResponseDTO(cart.get());
            } else {
                throw new CartNotFoundException(String.format("Not found %s", "cart"));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CartResponseDTO addItemsToCart(CartRequestDTO requestDTO) {
        try {
            Optional<Cart> cartOptional = cartRepository.findCartByCustomerId(requestDTO.getCustomerId());
            if (cartOptional.isPresent()) {
                var cart = cartOptional.get();
                if (!CollectionUtils.isEmpty(requestDTO.getCartDetails())) {
                    for (var detailDto : requestDTO.getCartDetails()) {
                        var detail = new CartDetail();
                        Optional<Item> optionalItem = itemRepository.findItemById(detailDto.getItemId());

                        if (optionalItem.isPresent()) {
                            var item = optionalItem.get();
                            detail.setCart(cart);
                            if (detailDto.getQuantity() > 0) {
                                detail.setQuantity(detailDto.getQuantity());
                            } else {
                                throw new QuantityLessThanOneException(String.format("Item quantity should be bigger than one"));
                            }
                            detail.setItem(item);
                            cart.getCartDetails().add(detail);
                        } else {
                            throw new ItemNotFoundException(String.format("Not found %s", "item"));
                        }
                    }
                }
                cartRepository.save(cart);
            } else {
                throw new CartNotFoundException(String.format("Not found %s", "cart"));
            }

        } catch (Exception e) {
            throw e;
        }
        return findCartByCustomerId(requestDTO.getCustomerId());
    }

    @Override
    public void updateItemsToCart(CartRequestDTO requestDTO) {
        try {
            var cart = findCartByCustomerId(requestDTO.getCustomerId());
            if (!CollectionUtils.isEmpty(cart.getDetailDTOS())) {
                for (var detailDto : requestDTO.getCartDetails()) {
                    var cartDetailOptional = cartDetailRepository.findById(detailDto.getId());

                    if (cartDetailOptional.isPresent()) {
                        var cartDetail = cartDetailOptional.get();
                        Optional<Item> item = itemRepository.findItemById(detailDto.getItemId());
                        if (item.isPresent()) {
                            cartDetail.setItem(item.get());

                            if (detailDto.getQuantity() > 0) {
                                cartDetail.setQuantity(detailDto.getQuantity());
                            } else {
                                throw new QuantityLessThanOneException(String.format("Item quantity should be bigger than one"));
                            }
                        } else {
                            throw new ItemNotFoundException(String.format("Not found %s", "item"));
                        }
                        cartDetailRepository.save(cartDetail);
                    } else {
                        throw new CartDetailNotFoundException(String.format("Not found %s", "cart detail"));
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteItemInCartByCartDetailId(Integer cartDetailId) {
        try {
            cartDetailRepository.deleteById(cartDetailId);
        } catch (Exception e) {
            throw e;
        }
    }
}
