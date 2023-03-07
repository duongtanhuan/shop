package com.shop.service.impl;

import com.shop.dto.CartRequest;
import com.shop.dto.CartResponse;
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
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * CartServiceImpl.
 * */
@Service
public class CartServiceImpl implements ICartService {
  @Autowired
  private ItemRepository itemRepository;
  
  @Autowired
  private CartRepository cartRepository;
  
  @Autowired
  private CartDetailRepository cartDetailRepository;
  
  @Override
  public CartResponse findCartByCustomerId(Integer customerId) {
    try {
      Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
      if (cart.isPresent()) {
        return new CartResponse(cart.get());
      } else {
        // do not hardcode error message, please read the message from message.properties file
        throw new CartNotFoundException(String.format("Not found %s", "cart"));
      }
    } catch (Exception e) {
      // do not throw e, use particular exception
      throw e;
    }
  }
  
  @Override
  @Transactional(rollbackFor = Exception.class)
  public CartResponse addItemsToCart(CartRequest request) {
    CartResponse savedCartDto;
    Cart savedCart;
    try {
      Optional<Cart> cartOptional = cartRepository.findCartByCustomerId(request.getCustomerId());
      
      if (cartOptional.isPresent()) {
        var cart = cartOptional.get();
        
        if (!CollectionUtils.isEmpty(request.getCartDetails())) {
          for (var detailRequest : request.getCartDetails()) {
            var detail = new CartDetail();
            Optional<Item> optionalItem = itemRepository.findById(detailRequest.getItemId());
            
            if (optionalItem.isPresent()) {
              var item = optionalItem.get();
              detail.setCart(cart);
              
              if (detailRequest.getQuantity() > 0) {
                detail.setQuantity(detailRequest.getQuantity());
              } else {
                // do not hardcode error message, please read the message from message.properties file
                throw new QuantityLessThanOneException("Item quantity should be bigger than one");
              }
              detail.setItem(item);
              detail.setDateAdded(new Date());
              cart.getCartDetails().add(detail);
            } else {
              // do not hardcode error message, please read the message from message.properties file
              throw new ItemNotFoundException(String.format("Not found %s", "item"));
            }
          }
        }
        savedCart = cartRepository.save(cart);
        savedCartDto = new CartResponse(savedCart);
      } else {
        // do not hardcode error message, please read the message from message.properties file
        throw new CartNotFoundException(String.format("Not found %s", "cart"));
      }
      
    } catch (Exception e) {
      // use particular exception
      throw e;
    }
    return savedCartDto;
  }
  
  @Override
  public void updateItemsToCart(CartRequest request) {
    try {
      var cart = findCartByCustomerId(request.getCustomerId());
      if (!CollectionUtils.isEmpty(cart.getCartDetails())) {
        for (var detailDto : request.getCartDetails()) {
          var cartDetailOptional = cartDetailRepository.findById(detailDto.getId());
          
          if (cartDetailOptional.isPresent()) {
            var cartDetail = cartDetailOptional.get();
            Optional<Item> item = itemRepository.findById(detailDto.getItemId());
            if (item.isPresent()) {
              cartDetail.setItem(item.get());
              
              if (detailDto.getQuantity() > 0) {
                cartDetail.setQuantity(detailDto.getQuantity());
              } else {
                // do not hardcode error message, please read the message from message.properties file
                throw new QuantityLessThanOneException("Item quantity should be bigger than one");
              }
            } else {
              // do not hardcode error message, please read the message from message.properties file
              throw new ItemNotFoundException(String.format("Not found %s", "item"));
            }
            cartDetailRepository.save(cartDetail);
          } else {
            // do not hardcode error message, please read the message from message.properties file
            throw new CartDetailNotFoundException(String.format("Not found %s", "cart detail"));
          }
        }
      }
    } catch (Exception e) {
      // use particular exception
      throw e;
    }
  }
  
  @Override
  public void deleteItemInCartByCartDetailId(Integer cartDetailId) {
    try {
      cartDetailRepository.deleteById(cartDetailId);
    } catch (Exception e) {
      // use particular exception
      throw e;
    }
  }
}
