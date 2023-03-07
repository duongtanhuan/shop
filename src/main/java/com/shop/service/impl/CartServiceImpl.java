package com.shop.service.impl;

import com.shop.dto.CartRequest;
import com.shop.dto.CartResponse;
import com.shop.exception.CartDetailCascadeDeleteError;
import com.shop.exception.CartDetailNotFoundException;
import com.shop.exception.CartNotFoundException;
import com.shop.exception.ItemNotFoundException;
import com.shop.exception.QuantityLessThanOneException;
import com.shop.exception.SystemErrorException;
import com.shop.model.Cart;
import com.shop.model.CartDetail;
import com.shop.model.Item;
import com.shop.repository.CartDetailRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.service.ICartService;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
  
  @Autowired
  private MessageSource messageSource;
  
  @Override
  public CartResponse findCartByCustomerId(Integer customerId) {
    Optional<Cart> cart = cartRepository.findCartByCustomerId(customerId);
    if (cart.isPresent()) {
      return new CartResponse(cart.get());
    } else {
      throw new CartNotFoundException(messageSource.getMessage("EBL201", null, Locale.ENGLISH));
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
                throw new QuantityLessThanOneException(messageSource.getMessage("EBL202", null,
                        Locale.ENGLISH));
              }
              detail.setItem(item);
              detail.setDateAdded(new Date());
              cart.getCartDetails().add(detail);
            } else {
              throw new ItemNotFoundException(messageSource.getMessage("EBL102", null,
                      Locale.ENGLISH));
            }
          }
        }
        savedCart = cartRepository.save(cart);
        savedCartDto = new CartResponse(savedCart);
      } else {
        throw new CartNotFoundException(messageSource.getMessage("EBL201", null, Locale.ENGLISH));
      }
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL203", null, Locale.ENGLISH));
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
                throw new QuantityLessThanOneException(messageSource.getMessage("EBL202", null,
                        Locale.ENGLISH));
              }
            } else {
              throw new ItemNotFoundException(messageSource.getMessage("EBL102", null,
                      Locale.ENGLISH));
            }
            cartDetailRepository.save(cartDetail);
          } else {
            throw new CartDetailNotFoundException(messageSource.getMessage("EBL204", null,
                    Locale.ENGLISH));
          }
        }
      }
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL205", null, Locale.ENGLISH));
    }
  }
  
  @Override
  public void deleteItemInCartByCartDetailId(Integer cartDetailId) {
    try {
      cartDetailRepository.deleteById(cartDetailId);
    } catch (Exception e) {
      throw new CartDetailCascadeDeleteError(String.format(messageSource.getMessage("EBL206", null,
              Locale.ENGLISH)));
    }
  }
}
