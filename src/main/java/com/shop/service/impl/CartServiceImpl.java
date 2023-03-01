package com.shop.service.impl;

import com.shop.dto.CartRequestDTO;
import com.shop.dto.CartResponseDTO;
import com.shop.exception.CartNotFoundException;
import com.shop.exception.CustomerNotFoundException;
import com.shop.mapper.CartMapper;
import com.shop.model.Cart;
import com.shop.model.CartDetail;
import com.shop.model.Customer;
import com.shop.model.Item;
import com.shop.repository.CartRepository;
import com.shop.repository.CustomerRepository;
import com.shop.repository.ItemRepository;
import com.shop.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CartResponseDTO findCartByCustomerId(Integer customerId) throws Exception {
        try {
            Optional<Cart> cart = cartRepository.findById(customerId);
            if (cart.isPresent()) {
                return CartMapper.INSTANCE.toDto(cart.get());
            } else {
                throw new CartNotFoundException(String.format("Not found %s", "cart"));
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CartResponseDTO addItemsToCart(CartRequestDTO dto) throws Exception {
        try {
            var cart = new Cart();
            Optional<Customer> optionalCustomer = customerRepository.findById(dto.getCustomerId());

            if (optionalCustomer.isPresent()) {
                if (optionalCustomer.get().getCart() == null) {
                    cart.setCustomer(optionalCustomer.get());
                } else {
                   //throw new SystemException(String.format("Excepted one to one relation"));
                }

            } else {
                throw new CustomerNotFoundException(String.format("Not found %s", "customer"));
            }

            if (!CollectionUtils.isEmpty(dto.getCartDetails())) {
                cart.setCartDetails(new ArrayList<>());

                for (var detailDto : dto.getCartDetails()) {
                    var detail = new CartDetail();
                    Optional<Item> optionalItem = itemRepository.findItemById(detailDto.getItemId());

                    if (optionalItem.isPresent()) {
                        var item = optionalItem.get();
                        detail.setCart(cart);
                        detail.setQuantity(detailDto.getQuantity());
                        detail.setItem(item);

                        cart.getCartDetails().add(detail);
                    } else {
                        throw new Exception();
                    }
                }
            }
            cartRepository.save(cart);

        } catch (Exception e) {
            throw new Exception();
        }

        return findCartByCustomerId(dto.getCustomerId());
    }

    @Override
    public void updateItemsToCart(CartRequestDTO dto) {

    }

    @Override
    public void deleteItemInCartByCartDetailId(Integer cartDetailId) {

    }
}
