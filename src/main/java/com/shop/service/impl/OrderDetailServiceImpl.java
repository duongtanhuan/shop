package com.shop.service.impl;

import com.shop.dto.OrderRequest;
import com.shop.dto.OrderResponse;
import com.shop.exception.CustomerNotFoundException;
import com.shop.exception.EmptyCartException;
import com.shop.exception.ItemNotFoundException;
import com.shop.exception.SystemErrorException;
import com.shop.mapper.OrderMapper;
import com.shop.model.Customer;
import com.shop.model.Order;
import com.shop.model.OrderDetail;
import com.shop.repository.CustomerRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.service.IOrderService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * OrderDetailServiceImpl.
 * */
@Service
public class OrderDetailServiceImpl implements IOrderService {
  @Autowired
  private CustomerRepository customerRepository;
  
  @Autowired
  private ItemRepository itemRepository;
  
  @Autowired
  private OrderRepository orderRepository;
  
  @Autowired
  private MessageSource messageSource;
  
  @Override
  public List<OrderResponse> getOrdersAllByCustomerId(Integer customerId) {
    try {
      List<Order> orders = orderRepository.findAllByCustomerId(customerId);
      return orders.stream().map(OrderMapper.INSTANCE::toDto)
              .collect(Collectors.toList());
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL301", null, Locale.ENGLISH));
    }
  }
  
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void createOrder(OrderRequest request) {
    try {
      var customerOptional = customerRepository.findById(request.getCustomerId());
      var order = setValuesForOrder(request, customerOptional);
      order.setCreateDate(new Date());
      orderRepository.save(order);
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL302", null, Locale.ENGLISH));
    }
  }
  
  private Order setValuesForOrder(OrderRequest request, Optional<Customer> customerOptional) {
    var order = new Order();
    if (customerOptional.isPresent()) {
    
      if (!CollectionUtils.isEmpty(customerOptional.get().getCart().getCartDetails())) {
        if (!CollectionUtils.isEmpty(request.getOrderDetails())) {
          order.setOrderDetails(new ArrayList<>());
          for (var detailRequest : request.getOrderDetails()) {
            var detail = new OrderDetail();
            var itemOptional = itemRepository.findById(detailRequest.getItemId());
            detail.setOrder(order);
          
            if (itemOptional.isPresent()) {
              detail.setItem(itemOptional.get());
            } else {
              throw new ItemNotFoundException(messageSource.getMessage("EBL102", null,
                      Locale.ENGLISH));
            }
            detail.setQuantity(detailRequest.getQuantity());
            order.getOrderDetails().add(detail);
          }
        }
        order.setCustomer(customerOptional.get());
      } else {
        throw new EmptyCartException(messageSource.getMessage("EBL303", null, Locale.ENGLISH));
      }
    } else {
      throw new CustomerNotFoundException(messageSource.getMessage("EBL304", null, Locale.ENGLISH));
    }
    return order;
  }
  
  @Override
  public OrderResponse getLatestOrderByCustomerId(Integer customerId) {
    try {
      List<Order> orders = orderRepository.findAllByCustomerId(customerId);
      Order order = new Order();
      
      if (!orders.isEmpty()) {
        order = orders.get(orders.size() - 1);
      }
      return OrderMapper.INSTANCE.toDto(order);
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL305", null, Locale.ENGLISH));
    }
  }
}
