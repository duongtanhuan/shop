package com.shop.service.impl;

import com.shop.dto.CartDetailResponse;
import com.shop.exception.CartDetailNotFoundException;
import com.shop.mapper.CartDetailMapper;
import com.shop.repository.CartDetailRepository;
import com.shop.service.ICartDetailService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * CartDetailImpl.
 * */
@Service
public class CartDetailImpl implements ICartDetailService {
  
  @Autowired
  private CartDetailRepository repository;
  
  @Autowired
  private MessageSource messageSource;
  
  @Override
  public CartDetailResponse findCartDetailByCustomerIdAndItemId(Integer cartId, Integer itemId) {
    try {
      return CartDetailMapper.INSTANCE.toDto(repository
              .findCartDetailByCartIdAndItemId(cartId, itemId));
    } catch (CartDetailNotFoundException e) {
      throw  new CartDetailNotFoundException(messageSource.getMessage("EBL204",
              null, Locale.ENGLISH));
    }
  }
  
  @Override
  public CartDetailResponse findCartDetailById(Integer id) {
    var cartDetail = repository.findById(id)
            .orElseThrow(() ->
                    new CartDetailNotFoundException(messageSource.getMessage("EBL204",
                            null, Locale.ENGLISH)));
    return CartDetailMapper.INSTANCE.toDto(cartDetail);
  }
}
