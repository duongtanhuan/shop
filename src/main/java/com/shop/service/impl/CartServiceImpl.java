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
  
  private CartRepository cartRepository;
  
  @Autowired
  private CartDetailRepository cartDetailRepository;
  
  @Autowired
  private MessageSource messageSource;
  
  @Override
  public CartResponse findCartByCustomerId(Integer customerId) {
    Cart cart = cartRepository.findCartByCustomerId(customerId).orElseThrow(() ->
            new CartNotFoundException(messageSource.getMessage("EBL201A", null, Locale.ENGLISH)));
    return new CartResponse(cart);
  }
  
  @Override
  @Transactional(rollbackFor = Exception.class)
  public CartResponse addItemsToCart(CartRequest request) {
    Cart savedCart;
    try {
      Cart cart = cartRepository.findCartByCustomerId(request.getCustomerId()).orElseThrow(() ->
              new CartNotFoundException(messageSource.getMessage("EBL201B", null, Locale.ENGLISH)));
      
      var detail = new CartDetail();
      Item item = itemRepository.findById(request.getCartDetail().getItemId()).orElseThrow(
              () -> new ItemNotFoundException(messageSource.getMessage(
                      "EBL102A", null, Locale.ENGLISH)));
      detail.setCart(cart);
      
      if (request.getCartDetail().getQuantity() > 0) {
        detail.setQuantity(request.getCartDetail().getQuantity());
      } else {
        throw new QuantityLessThanOneException(messageSource.getMessage("EBL202B", null,
                Locale.ENGLISH));
      }
      detail.setItem(item);
      detail.setDateAdded(new Date());
      cart.getCartDetails().add(detail);
      savedCart = cartRepository.save(cart);
    } catch (CartNotFoundException e) {
      throw  new CartNotFoundException(messageSource.getMessage("EBL201A", null, Locale.ENGLISH));
    } catch (ItemNotFoundException e) {
      throw new ItemNotFoundException(messageSource.getMessage("EBL102A", null, Locale.ENGLISH));
    }  catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL203", null, Locale.ENGLISH));
    }
    return new CartResponse(savedCart);
  }
  
  @Override
  public void updateItemsToCart(CartRequest request) {
    try {
      var cart = findCartByCustomerId(request.getCustomerId());
      if (!CollectionUtils.isEmpty(cart.getCartDetails())) {
        var cartDetail = cartDetailRepository.findById(request.getCartDetail().getId())
                .orElseThrow(() ->
                        new CartDetailNotFoundException(messageSource.getMessage("EBL204",
                        null, Locale.ENGLISH)));
        
        var item = itemRepository.findById(request.getCartDetail().getItemId())
                .orElseThrow(() -> new ItemNotFoundException(
                        messageSource.getMessage("EBL102B", null, Locale.ENGLISH)));
        
        cartDetail.setItem(item);
        
        if (request.getCartDetail().getQuantity() > 0) {
          cartDetail.setQuantity(request.getCartDetail().getQuantity());
        } else {
          throw new QuantityLessThanOneException(messageSource.getMessage("EBL202", null,
                  Locale.ENGLISH));
        }
        cartDetailRepository.save(cartDetail);
      }
    } catch (QuantityLessThanOneException e) {
      throw new QuantityLessThanOneException(messageSource.getMessage("EBL202", null,
              Locale.ENGLISH));
    } catch (ItemNotFoundException e) {
      throw new ItemNotFoundException(messageSource.getMessage("EBL102C", null, Locale.ENGLISH));
    } catch (CartDetailNotFoundException e) {
      throw new CartDetailNotFoundException(messageSource.getMessage("EBL204",
              null, Locale.ENGLISH));
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
